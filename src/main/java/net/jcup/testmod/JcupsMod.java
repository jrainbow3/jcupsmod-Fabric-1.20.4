package net.jcup.testmod;

import net.fabricmc.api.ModInitializer;

import net.jcup.testmod.block.ModBlocks;
import net.jcup.testmod.item.ModItemGroups;
import net.jcup.testmod.item.ModItems;
import net.jcup.testmod.particle.ModParticles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JcupsMod implements ModInitializer {
	public static final String MOD_ID = "jcupsmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModParticles.registerModParticles();
	}
}