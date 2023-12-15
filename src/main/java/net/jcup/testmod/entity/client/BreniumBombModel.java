package net.jcup.testmod.entity.client;
import net.jcup.testmod.entity.custom.BreniumBombEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class BreniumBombModel<T extends Entity> extends EntityModel<T> {
    private final ModelPart bomb;

    public BreniumBombModel() {
        this.bomb = getTexturedModelData().createModel().getChild("cube");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData data = new ModelData();
        ModelPartData partDefinition = data.getRoot();

        // Define a simple 1x1x1 cube
        partDefinition.addChild("cube", ModelPartBuilder.create().cuboid(-0.5F, -0.5F, -0.5F, 16, 16, 16), ModelTransform.NONE);
        return TexturedModelData.of(data, 16, 16);
    }

    @Override
    public void render(MatrixStack matrices, net.minecraft.client.render.VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        bomb.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    public void setAngles(Entity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }
}