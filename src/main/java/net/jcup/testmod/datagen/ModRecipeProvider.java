package net.jcup.testmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.jcup.testmod.block.ModBlocks;
import net.jcup.testmod.item.ModItems;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> BRENIUM_SMELTABLES = List.of(ModBlocks.BRENIUM_ORE, ModBlocks.END_STONE_BRENIUM_ORE);

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerSmelting(exporter, BRENIUM_SMELTABLES, RecipeCategory.MISC, ModItems.BRENIUM, 1.5f, 200, "brenium");
        offerBlasting(exporter, BRENIUM_SMELTABLES, RecipeCategory.MISC, ModItems.BRENIUM, 1.5f, 100, "brenium");
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.BRENIUM, RecipeCategory.DECORATIONS, ModBlocks.BRENIUM_BLOCK);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.BRENIUM_STAFF, 1)
                .pattern("B")
                .pattern("R")
                .input('B', ModItems.BRENIUM)
                .input('R', Items.BLAZE_ROD)
                .criterion(hasItem(ModItems.BRENIUM), conditionsFromItem(ModItems.BRENIUM))
                .offerTo(exporter);
    }
}
