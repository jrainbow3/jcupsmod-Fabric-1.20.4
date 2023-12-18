package net.jcup.testmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.jcup.testmod.entity.ModEntities;
import net.jcup.testmod.entity.client.BreniumBombModel;
import net.jcup.testmod.entity.client.BreniumBombRenderer;
import net.jcup.testmod.entity.client.ModModelLayers;
import net.jcup.testmod.particle.ModParticles;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.render.entity.TntEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;

public class JcupsModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.BRENIUM_BOMB, BreniumBombRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BRENIUM_BOMB, BreniumBombModel::getTexturedModelData);

        ParticleFactoryRegistry.getInstance().register(ModParticles.BREN_FACE, FlameParticle.Factory::new);
    }
}