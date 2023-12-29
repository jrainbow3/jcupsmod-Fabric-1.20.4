package net.jcup.testmod.entity.client;

import net.jcup.testmod.JcupsMod;
import net.jcup.testmod.entity.custom.JojoEntity;
import net.jcup.testmod.entity.custom.KateEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class JojoRenderer extends MobEntityRenderer<JojoEntity, JojoModel<JojoEntity>> {
    private static final Identifier TEXTURE = new Identifier(JcupsMod.MOD_ID, "textures/entity/jojo.png");
    public JojoRenderer(EntityRendererFactory.Context context) {
        super(context, new JojoModel<>(context.getPart(ModModelLayers.JOJO)), 1.0f);
    }
    @Override
    public void render(JojoEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
    @Override
    public Identifier getTexture(JojoEntity entity) {
        return TEXTURE;
    }
}
