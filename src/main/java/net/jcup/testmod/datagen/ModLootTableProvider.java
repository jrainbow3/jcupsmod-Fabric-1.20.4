package net.jcup.testmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.jcup.testmod.block.ModBlocks;
import net.jcup.testmod.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.BRENIUM_BLOCK);
        addDrop(ModBlocks.BRENIUM_BOMB);
        addDrop(ModBlocks.BRENIUM_EXTRACTOR);

        addDrop(ModBlocks.BRENIUM_ORE, multiOreDrops(ModBlocks.BRENIUM_ORE, ModItems.BRENIUM, 2, 5));
        addDrop(ModBlocks.END_STONE_BRENIUM_ORE, multiOreDrops(ModBlocks.END_STONE_BRENIUM_ORE, ModItems.BRENIUM, 4 ,6));
    }

    public LootTable.Builder multiOreDrops(Block drop, Item item, float min, float max) {
        return BlockLootTableGenerator.dropsWithSilkTouch(drop, (LootPoolEntry.Builder)this.applyExplosionDecay(drop,
                ((LeafEntry.Builder)
                        ItemEntry.builder(item)
                                .apply(SetCountLootFunction
                                        .builder(UniformLootNumberProvider.create(min, max))))
                        .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }
}
