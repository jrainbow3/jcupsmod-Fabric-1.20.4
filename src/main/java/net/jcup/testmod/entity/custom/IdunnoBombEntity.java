package net.jcup.testmod.entity.custom;

import net.jcup.testmod.entity.ModEntities;
import net.jcup.testmod.particle.ModParticles;
import net.jcup.testmod.sound.ModSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.TntEntity;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;

public class IdunnoBombEntity extends TntEntity {
    private LivingEntity igniter;
    public IdunnoBombEntity(EntityType<? extends TntEntity> entityType, World world) {
        super(entityType, world);
    }

    public static IdunnoBombEntity create(World world, double x, double y, double z, LivingEntity igniter, float fuseRemaining, boolean isChild) {
        IdunnoBombEntity entity = new IdunnoBombEntity(ModEntities.IDUNNO_BOMB, world);
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
        //if(!this.getWorld().isClient && i % 5 == 0) this.getWorld().playSound(null, getX(), getY(), getZ(), ModSounds.BRENIUM_BOMB_FUSE, SoundCategory.BLOCKS);
        if (i <= 0) {
            this.discard();
            if (!this.getWorld().isClient) {
                this.explode();
            }
        } else {
            this.updateWaterState();
            if (this.getWorld().isClient) {
                this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
                //spawnParticles();
            }
        }
    }

    private void spawnParticles() {
        for(int i = 0; i < 50; i++) {
            this.getWorld().addParticle(ModParticles.BREN_FACE, getX(), getY()+0.5, getZ(), getX() + (Math.random()-.5) * 4, getY() + Math.random() * 4, getZ() + (Math.random()-.5) * 4);
        }
    }

    private void explode() {

        World world = this.getWorld();
        if(world == null || world.isClient()) return;

        world.createExplosion(this, null, null, getX(), getY(), getZ(), 5.0F, true, World.ExplosionSourceType.TNT);

        this.discard();
    }
}
