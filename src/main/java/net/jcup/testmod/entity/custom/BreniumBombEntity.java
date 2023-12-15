package net.jcup.testmod.entity.custom;

import net.jcup.testmod.entity.ModEntities;
import net.jcup.testmod.entity.client.BreniumBombRenderer;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class BreniumBombEntity extends TntEntity {
    private LivingEntity igniter;
    public BreniumBombEntity(EntityType<? extends TntEntity> entityType, World world) {
        super(entityType, world);
    }

    public static BreniumBombEntity create(World world, double x, double y, double z, LivingEntity igniter) {
        BreniumBombEntity entity = new BreniumBombEntity(ModEntities.BRENIUM_BOMB, world);
        entity.updatePosition(x, y, z);
        entity.igniter = igniter;
        return entity;
    }

    @Override
    public void tick() {
        if (!this.hasNoGravity()) {
            this.setVelocity(this.getVelocity().add(0.0, -0.04, 0.0));
        }
        this.move(MovementType.SELF, this.getVelocity());
        this.setVelocity(this.getVelocity().multiply(0.98));
        if (this.isOnGround()) {
            this.setVelocity(this.getVelocity().multiply(0.7, -0.5, 0.7));
        }
        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            this.discard();
            if (!this.getWorld().isClient) {
                this.explode();
            }
        } else {
            this.updateWaterState();
            if (this.getWorld().isClient) {
                this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    private void explode() {

        World world = this.getWorld();
        if(world == null || world.isClient()) return;
        world.createExplosion(this, getX(), getY(), getZ(), 20.0F, true, World.ExplosionSourceType.TNT);

        this.discard();
    }
}
