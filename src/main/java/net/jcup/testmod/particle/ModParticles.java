package net.jcup.testmod.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.jcup.testmod.JcupsMod;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final DefaultParticleType BREN_FACE = FabricParticleTypes.simple();

    public static void registerModParticles() {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(JcupsMod.MOD_ID, "bren_face"), BREN_FACE);
    }
}
