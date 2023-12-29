package net.jcup.testmod.entity.client;

import net.jcup.testmod.JcupsMod;
import net.jcup.testmod.entity.custom.KateEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class KateRenderer extends MobEntityRenderer<KateEntity, KateModel<KateEntity>> {
    private static final Identifier TEXTURE = new Identifier(JcupsMod.MOD_ID, "textures/entity/kate.png");
    public KateRenderer(EntityRendererFactory.Context context) {
        super(context, new KateModel<>(context.getPart(ModModelLayers.KATE)), 1.0f);
    }
    @Override
    public void render(KateEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
    @Override
    public Identifier getTexture(KateEntity entity) {
        return TEXTURE;
    }
}
