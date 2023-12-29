package net.jcup.testmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.jcup.testmod.block.ModBlocks;
import net.jcup.testmod.entity.ModEntities;
import net.jcup.testmod.entity.custom.JojoEntity;
import net.jcup.testmod.entity.custom.KateEntity;
import net.jcup.testmod.item.ModItemGroups;
import net.jcup.testmod.item.ModItems;
import net.jcup.testmod.particle.ModParticles;
import net.jcup.testmod.sound.ModSounds;
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
		ModSounds.registerSounds();

		FabricDefaultAttributeRegistry.register(ModEntities.KATE, KateEntity.createKateAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.JOJO, JojoEntity.createJojoAttributes());
	}
}