package net.jcup.testmod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.jcup.testmod.JcupsMod;
import net.jcup.testmod.entity.custom.BreniumBombEntity;
import net.jcup.testmod.entity.custom.JojoEntity;
import net.jcup.testmod.entity.custom.KateEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<BreniumBombEntity> BRENIUM_BOMB = Registry.register(Registries.ENTITY_TYPE,
        new Identifier(JcupsMod.MOD_ID, "brenium_bomb"),
        FabricEntityTypeBuilder.create(SpawnGroup.MISC, BreniumBombEntity::new).dimensions(EntityDimensions.fixed(1f, 1f)).build());
    public static final EntityType<KateEntity> KATE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(JcupsMod.MOD_ID, "kate"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, KateEntity::new).dimensions(EntityDimensions.fixed(1, 4f)).build());
    public static final EntityType<JojoEntity> JOJO = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(JcupsMod.MOD_ID, "jojo"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, JojoEntity::new).dimensions(EntityDimensions.fixed(5, 5)).build());

    public static void registerModEntities() {
        JcupsMod.LOGGER.info("Registering Entities for " + JcupsMod.MOD_ID);
    }
}
