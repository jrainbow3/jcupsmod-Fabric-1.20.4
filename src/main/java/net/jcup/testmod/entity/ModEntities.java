package net.jcup.testmod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.jcup.testmod.JcupsMod;
import net.jcup.testmod.entity.custom.BreniumBombEntity;
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
    public static void registerModEntities() {
        JcupsMod.LOGGER.info("Registering Entities for " + JcupsMod.MOD_ID);
    }
}
