package net.jcup.testmod.entity.custom;

import net.jcup.testmod.entity.ModEntities;
import net.jcup.testmod.particle.ModParticles;
import net.jcup.testmod.sound.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;

public class IdunnoBombEntity extends TntEntity {
    private LivingEntity igniter;
    private static final TrackedData<Integer> FUSE;
    private static final TrackedData<BlockState> BLOCK_STATE;

    private int startFuse;
    private static final int DEFAULT_FUSE = 120;

    public IdunnoBombEntity(EntityType<? extends TntEntity> entityType, World world) {
        super(entityType, world);
    }

    public static IdunnoBombEntity create(World world, double x, double y, double z, LivingEntity igniter, int fuseRemaining, boolean isChild) {
        IdunnoBombEntity entity = new IdunnoBombEntity(ModEntities.IDUNNO_BOMB, world);
        entity.updatePosition(x, y, z);
        entity.setFuse(fuseRemaining);
        entity.startFuse = fuseRemaining;
        if(!isChild) {
            double d = world.random.nextDouble() * 6.2831854820251465;
            entity.setVelocity(-Math.sin(d) * 0.02, 0.20000000298023224, -Math.cos(d) * 0.02);
        }
        entity.igniter = igniter;
        return entity;
    }

    public static IdunnoBombEntity create(World world, double x, double y, double z, LivingEntity igniter) {
        return create(world, x, y, z, igniter, DEFAULT_FUSE, false);
    }

    @Override
    public void tick() {
        World world = this.getWorld();
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
        if(i % 8 == 0 && i != startFuse && i > DEFAULT_FUSE/2) {
            IdunnoBombEntity idunnoBomb = IdunnoBombEntity.create(world, this.getX(), this.getY() + 0.5, this.getZ(), igniter, i, true);
            idunnoBomb.setVelocity((Math.random()-0.5)*4, 1, (Math.random()-0.5)*4);
            world.spawnEntity(idunnoBomb);
        }
        //if(!this.getWorld().isClient && i % 5 == 0) this.getWorld().playSound(null, getX(), getY(), getZ(), ModSounds.BRENIUM_BOMB_FUSE, SoundCategory.BLOCKS);
        if (i <= 0) {
            this.discard();
            if (!world.isClient) {
                this.explode();
            }
        } else {
            this.updateWaterState();
            if (world.isClient) {
                world.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
                //spawnParticles();
            }
        }
    }

    protected void initDataTracker() {
        this.dataTracker.startTracking(FUSE, DEFAULT_FUSE);
        this.dataTracker.startTracking(BLOCK_STATE, Blocks.TNT.getDefaultState());
    }

    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putShort("fuse", (short)this.getFuse());
        nbt.put("block_state", NbtHelper.fromBlockState(this.getBlockState()));
    }

    protected void readCustomDataFromNbt(NbtCompound nbt) {
        this.setFuse(nbt.getShort("fuse"));
        if (nbt.contains("block_state", 10)) {
            this.setBlockState(NbtHelper.toBlockState(this.getWorld().createCommandRegistryWrapper(RegistryKeys.BLOCK), nbt.getCompound("block_state")));
        }

    }

    public void setBlockState(BlockState state) {
        this.dataTracker.set(BLOCK_STATE, state);
    }

    public BlockState getBlockState() {
        return (BlockState)this.dataTracker.get(BLOCK_STATE);
    }
    @Override
    public boolean hasNoGravity() {
        return false;
    }

    private void spawnParticles() {
        for(int i = 0; i < 50; i++) {
            this.getWorld().addParticle(ModParticles.BREN_FACE, getX(), getY()+0.5, getZ(), getX() + (Math.random()-.5) * 4, getY() + Math.random() * 4, getZ() + (Math.random()-.5) * 4);
        }
    }

    public void setFuse(int fuse) {
        this.dataTracker.set(FUSE, fuse);
    }

    public int getFuse() {
        return (Integer)this.dataTracker.get(FUSE);
    }

    private void explode() {

        World world = this.getWorld();
        if(world == null || world.isClient()) return;

        world.createExplosion(this, null, null, getX(), getY(), getZ(), 5.0F, false, World.ExplosionSourceType.TNT);

        this.discard();
    }

    static {
        FUSE = DataTracker.registerData(IdunnoBombEntity.class, TrackedDataHandlerRegistry.INTEGER);
        BLOCK_STATE = DataTracker.registerData(IdunnoBombEntity.class, TrackedDataHandlerRegistry.BLOCK_STATE);
    }
}
