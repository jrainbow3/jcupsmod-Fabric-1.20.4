package net.jcup.testmod.entity.custom;

import net.jcup.testmod.sound.ModSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class JojoEntity extends MobEntity implements Monster {

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public JojoEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new JojoEntity.JojoMoveControl(this);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(2, new FaceTowardTargetGoal(this));
        this.goalSelector.add(3, new RandomLookGoal(this));
        this.goalSelector.add(5, new MoveGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, 10, true, false, (livingEntity) -> {
            return Math.abs(livingEntity.getY() - this.getY()) <= 4.0;
        }));
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 5f;
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            this.idleAnimationTimeout--;
        }
    }

    public static DefaultAttributeContainer.Builder createJojoAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.0f)
                .add(EntityAttributes.GENERIC_ARMOR, 5)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 0.3f);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.getWorld().isClient()) {
            setupAnimationStates();
        }
    }
    public void onPlayerCollision(PlayerEntity player) {
        this.damage(player);
    }

    protected void damage(LivingEntity target) {
        if (this.isAlive()) {
            int i = this.getSize();
            if (this.squaredDistanceTo(target) < 0.6 * (double)i * 0.6 * (double)i && this.canSee(target) && target.damage(this.getDamageSources().mobAttack(this), this.getDamageAmount())) {
                this.playSound(SoundEvents.ENTITY_SLIME_ATTACK, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                this.applyDamageEffects(this, target);
            }
        }

    }

    private float getDamageAmount() {
        return (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
    }

    public int getSize() {
        return 5; // TODO implement size functionality
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6, 1) : 0;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return (Math.random() > 0.5f) ? ModSounds.KATE_AMBIENT_1 : ModSounds.KATE_AMBIENT_2;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.KATE_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource dmg) {
        return ModSounds.KATE_HURT;
    }

    protected int getTicksUntilNextJump() {
        return this.random.nextInt(20) + 50;
    }

    protected void jump() {
        Vec3d vec3d = this.getVelocity();
        //vec3d.multiply(8);
        this.setVelocity(vec3d.x, (double)this.getJumpVelocity()*1, vec3d.z);
        this.velocityDirty = true;
    }

    static class FaceTowardTargetGoal extends Goal {
        private final JojoEntity jojo;
        private int ticksLeft;

        public FaceTowardTargetGoal(JojoEntity jojo) {
            this.jojo = jojo;
            this.setControls(EnumSet.of(Control.LOOK));
        }

        public boolean canStart() {
            LivingEntity livingEntity = this.jojo.getTarget();
            if (livingEntity == null) {
                return false;
            } else {
                return this.jojo.canTarget(livingEntity) && this.jojo.getMoveControl() instanceof JojoMoveControl;
            }
        }

        public void start() {
            this.ticksLeft = toGoalTicks(300);
            super.start();
        }

        public boolean shouldContinue() {
            LivingEntity livingEntity = this.jojo.getTarget();
            if (livingEntity == null) {
                return false;
            } else if (!this.jojo.canTarget(livingEntity)) {
                return false;
            } else {
                return --this.ticksLeft > 0;
            }
        }

        public boolean shouldRunEveryTick() {
            return true;
        }

        public void tick() {
            LivingEntity livingEntity = this.jojo.getTarget();
            if (livingEntity != null) {
                this.jojo.lookAtEntity(livingEntity, 10.0F, 10.0F);
            }

            MoveControl var3 = this.jojo.getMoveControl();
            if (var3 instanceof JojoEntity.JojoMoveControl jojoMoveControl) {
                jojoMoveControl.look(this.jojo.getYaw());
            }

        }
    }

    static class RandomLookGoal extends Goal {
        private final JojoEntity jojo;
        private float targetYaw;
        private int timer;

        public RandomLookGoal(JojoEntity jojo) {
            this.jojo = jojo;
            this.setControls(EnumSet.of(Control.LOOK));
        }

        public boolean canStart() {
            return this.jojo.getTarget() == null && (this.jojo.isOnGround() || this.jojo.isTouchingWater() || this.jojo.isInLava() || this.jojo.hasStatusEffect(StatusEffects.LEVITATION)) && this.jojo.getMoveControl() instanceof JojoEntity.JojoMoveControl;
        }

        public void tick() {
            if (--this.timer <= 0) {
                this.timer = this.getTickCount(40 + this.jojo.getRandom().nextInt(60));
                this.targetYaw = (float)this.jojo.getRandom().nextInt(360);
            }

            MoveControl var2 = this.jojo.getMoveControl();
            if (var2 instanceof JojoEntity.JojoMoveControl jojoMoveControl) {
                jojoMoveControl.look(this.targetYaw);
            }

        }
    }

    private static class JojoMoveControl extends MoveControl {
        private float targetYaw;
        private int ticksUntilRoll;
        private final JojoEntity jojo;

        public JojoMoveControl(JojoEntity jojo) {
            super(jojo);
            this.jojo = jojo;
            this.targetYaw = 180.0F * jojo.getYaw() / 3.1415927F;
        }

        public void look(float targetYaw) {
            this.targetYaw = targetYaw;
        }

        public void move(double speed) {
            this.speed = speed;
            this.state = State.MOVE_TO;
        }

        public void tick() {
            this.entity.setYaw(this.wrapDegrees(this.entity.getYaw(), this.targetYaw, 90.0F));
            this.entity.headYaw = this.entity.getYaw();
            this.entity.bodyYaw = this.entity.getYaw();
            if (this.state != State.MOVE_TO) {
                this.entity.setForwardSpeed(0.0F);
            } else {
                this.state = State.WAIT;
                if (this.entity.isOnGround()) {
                    this.entity.setMovementSpeed((float)(this.speed * this.entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
                    if (this.ticksUntilRoll-- <= 0) {
                        this.ticksUntilRoll = this.jojo.getTicksUntilNextJump();

                        this.jojo.getJumpControl().setActive();
                    } else {
                        this.jojo.sidewaysSpeed = 0.0F;
                        this.jojo.forwardSpeed = 0.0F;
                        this.entity.setMovementSpeed(0.0F);
                    }
                } else {
                    this.entity.setMovementSpeed((float)(this.speed * this.entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
                }

            }
        }
    }
    static class MoveGoal extends Goal {
        private final JojoEntity jojo;

        public MoveGoal(JojoEntity jojo) {
            this.jojo = jojo;
            this.setControls(EnumSet.of(Control.JUMP, Control.MOVE));
        }

        public boolean canStart() {
            return !this.jojo.hasVehicle();
        }

        public void tick() {
            MoveControl var2 = this.jojo.getMoveControl();
            if (var2 instanceof JojoEntity.JojoMoveControl jojoMoveControl) {
                jojoMoveControl.move(5.0);
            }

        }
    }
}
