package net.jcup.testmod.entity.custom;

import net.jcup.testmod.sound.ModSounds;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class KateEntity extends EndermanEntity {

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public KateEntity(EntityType<? extends EndermanEntity> entityType, World world) {
        super(entityType, world);
    }

//    @Override
//    protected void initGoals() {
//        this.goalSelector.add(0, new SwimGoal(this));
//        this.goalSelector.add(1, new ChasePlayerGoal(this));
//        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.0, false));
//        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0, 0.0F));
//        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
//        this.goalSelector.add(8, new LookAroundGoal(this));
//        this.targetSelector.add(2, new RevengeGoal(this, new Class[0]));
//        this.targetSelector.add(3, new ActiveTargetGoal(this, EndermiteEntity.class, true, false));
//        this.targetSelector.add(4, new UniversalAngerGoal(this, false));
//    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 1.5f;
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            this.idleAnimationTimeout--;
        }
    }

    public static DefaultAttributeContainer.Builder createKateAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.7f)
                .add(EntityAttributes.GENERIC_ARMOR, 0)
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

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6, 1) : 0;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if(isAngry()) return ModSounds.KATE_ANGRY;
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

}
