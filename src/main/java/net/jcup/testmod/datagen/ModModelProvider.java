package net.jcup.testmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.jcup.testmod.block.ModBlocks;
import net.jcup.testmod.item.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BRENIUM_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BRENIUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.END_STONE_BRENIUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BRENIUM_EXTRACTOR);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BRENIUM_BOMB);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.IDUNNO_BOMB);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.BRENAXE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BRENIUM, Models.GENERATED);
        itemModelGenerator.register(ModItems.BRENIUM_STAFF, Models.GENERATED);
        itemModelGenerator.register(ModItems.CONCENTRATED_BRENIUM, Models.GENERATED);

        itemModelGenerator.register(ModItems.KATE_SPAWN_EGG,
                new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));
        itemModelGenerator.register(ModItems.JOJO_SPAWN_EGG,
                new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));

    }
}
