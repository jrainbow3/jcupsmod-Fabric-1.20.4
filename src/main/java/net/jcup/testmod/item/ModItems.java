package net.jcup.testmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.jcup.testmod.JcupsMod;
import net.jcup.testmod.entity.ModEntities;
import net.jcup.testmod.item.custom.BreniumStaffItem;
import net.jcup.testmod.item.custom.ConcentratedBreniumItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item BRENAXE = registerItem("brenaxe", new Item(new FabricItemSettings()));
    public static final Item BRENIUM = registerItem("brenium", new Item(new FabricItemSettings()));
    public static final Item CONCENTRATED_BRENIUM = registerItem("concentrated_brenium", new ConcentratedBreniumItem(new FabricItemSettings()));
    public static final Item BRENIUM_STAFF = registerItem("brenium_staff", new BreniumStaffItem(new FabricItemSettings().maxDamage(64)));

    public static final Item KATE_SPAWN_EGG = registerItem("kate_spawn_egg",
            new SpawnEggItem(ModEntities.KATE, 0x5f71ff, 0xd1ff6c, new FabricItemSettings()));
    public static final Item JOJO_SPAWN_EGG = registerItem("jojo_spawn_egg",
            new SpawnEggItem(ModEntities.JOJO, 0xffffff, 0xffac39, new FabricItemSettings()));
    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(BRENIUM);
        entries.add(CONCENTRATED_BRENIUM);
    }

    private static void addItemsToToolItemGroup(FabricItemGroupEntries entries) {
        entries.add(BRENAXE);
        entries.add(BRENIUM_STAFF);
    }

    private static void addItemsToSpawnEggGroup(FabricItemGroupEntries entries) {
        entries.add(KATE_SPAWN_EGG);
        entries.add(JOJO_SPAWN_EGG);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(JcupsMod.MOD_ID, name), item);
    }
    public static void registerModItems() {
        JcupsMod.LOGGER.info("Registering Mod Items for " + JcupsMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToToolItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(ModItems::addItemsToSpawnEggGroup);
    }
}
