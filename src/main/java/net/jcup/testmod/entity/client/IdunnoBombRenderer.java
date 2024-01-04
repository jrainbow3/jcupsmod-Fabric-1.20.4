package net.jcup.testmod.entity.client;

import net.jcup.testmod.JcupsMod;
import net.jcup.testmod.entity.custom.BreniumBombEntity;
import net.jcup.testmod.entity.custom.IdunnoBombEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class IdunnoBombRenderer extends EntityRenderer<IdunnoBombEntity> {
    private static final Identifier TEXTURE = new Identifier(JcupsMod.MOD_ID, "textures/entity/brenium_bomb.png");
    private EntityModel<IdunnoBombEntity> model;

    public IdunnoBombRenderer(EntityRendererFactory.Context context) {
        super(context);
        model = new BreniumBombModel<>();
    }

    @Override
    public Identifier getTexture(IdunnoBombEntity entity) {
        return TEXTURE;
    }

    public void render(IdunnoBombEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        //matrices.scale(1.0F, 1.0F, 1.0F);

        // Adjust the translation based on the model size and position
        matrices.translate(-0.5D, 0.0D, -0.5D);

        // You may need to adjust the rotation based on your model and entity's properties
        float rotation = 0.0F;
        this.model.render(matrices, vertexConsumers.getBuffer(this.model.getLayer(TEXTURE)), light, 0, 1.0F, 1.0F, 1.0F, 1.0F);

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

}
