package net.jcup.testmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.jcup.testmod.entity.ModEntities;
import net.jcup.testmod.entity.client.BreniumBombModel;
import net.jcup.testmod.entity.client.BreniumBombRenderer;
import net.jcup.testmod.entity.client.ModModelLayers;
import net.minecraft.client.render.entity.TntEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class JcupsModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.BRENIUM_BOMB, BreniumBombRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BRENIUM_BOMB, BreniumBombModel::getTexturedModelData);

    }
}