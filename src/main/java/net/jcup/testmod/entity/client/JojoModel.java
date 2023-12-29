package net.jcup.testmod.entity.client;

import net.jcup.testmod.entity.animation.ModAnimations;
import net.jcup.testmod.entity.custom.JojoEntity;
import net.jcup.testmod.entity.custom.KateEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.9.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class JojoModel<T extends JojoEntity> extends SinglePartEntityModel<T> {
	private final ModelPart jojo;
	private final ModelPart head;
	public JojoModel(ModelPart root) {
		this.jojo = root.getChild("jojo");
		this.head = jojo.getChild("body").getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData jojo = modelPartData.addChild("jojo", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData body = jojo.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -11.0F, 0.0F));

		ModelPartData leg1 = body.addChild("leg1", ModelPartBuilder.create().uv(0, 14).cuboid(-10.0F, -2.0F, -13.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 11.0F, 0.0F));

		ModelPartData leg2 = body.addChild("leg2", ModelPartBuilder.create().uv(12, 0).cuboid(8.0F, -2.0F, -13.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 11.0F, 0.0F));

		ModelPartData arm1 = body.addChild("arm1", ModelPartBuilder.create().uv(0, 8).cuboid(-13.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, 0.0F));

		ModelPartData arm2 = body.addChild("arm2", ModelPartBuilder.create().uv(8, 8).cuboid(11.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, 0.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -11.0F, 0.0F));

		ModelPartData torso = body.addChild("torso", ModelPartBuilder.create().uv(0, 0).cuboid(-11.0F, -11.0F, -11.0F, 22.0F, 22.0F, 22.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public void setAngles(JojoEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);

		this.animateMovement(ModAnimations.JOJO_WALK, limbSwing, limbSwingAmount, 2, 2.5f);
		this.updateAnimation(entity.idleAnimationState, ModAnimations.JOJO_IDLE, ageInTicks, 1);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30, 30);
		headPitch = MathHelper.clamp(headPitch, -25, 45);

		this.head.yaw = headYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		matrices.scale(3, 3 ,3);
		matrices.translate(0D,-1.0D, 0D);
		jojo.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return jojo;
	}

}