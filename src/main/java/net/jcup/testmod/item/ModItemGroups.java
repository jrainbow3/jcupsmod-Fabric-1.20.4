package net.jcup.testmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.jcup.testmod.JcupsMod;
import net.jcup.testmod.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup BREN_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(JcupsMod.MOD_ID, "brenium"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.brenium"))
                    .icon(() -> new ItemStack(ModItems.BRENIUM)).entries((displayContext, entries) -> {
                        entries.add(ModItems.BRENIUM);
                        entries.add(ModItems.BRENAXE);
                        entries.add(ModBlocks.BRENIUM_BLOCK);
                        entries.add(ModBlocks.BRENIUM_EXTRACTOR);
                    }).build());

    public static void registerItemGroups() {
        JcupsMod.LOGGER.info("Registering Item Groups for " + JcupsMod.MOD_ID);
    }
}
