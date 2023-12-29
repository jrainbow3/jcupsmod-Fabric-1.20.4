package net.jcup.testmod.entity.client;

import net.jcup.testmod.entity.animation.ModAnimations;
import net.jcup.testmod.entity.custom.KateEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.9.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class KateModel<T extends KateEntity> extends SinglePartEntityModel<T> {
	private final ModelPart kate;
	private final ModelPart head;

	public KateModel(ModelPart root) {
		this.kate = root.getChild("kate");
		this.head = kate.getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData kate = modelPartData.addChild("kate", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData torso = kate.addChild("torso", ModelPartBuilder.create().uv(0, 20).cuboid(-5.0F, -49.0F, -2.0F, 10.0F, 25.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_leg = kate.addChild("right_leg", ModelPartBuilder.create().uv(38, 20).cuboid(-5.0F, 0.0F, -1.0F, 1.0F, 24.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -24.0F, 0.0F));

		ModelPartData left_leg = kate.addChild("left_leg", ModelPartBuilder.create().uv(34, 20).cuboid(8.0F, 0.0F, -1.0F, 1.0F, 24.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, -24.0F, 0.0F));

		ModelPartData head = kate.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -10.0F, -6.0F, 10.0F, 10.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -49.0F, 0.0F));

		ModelPartData right_arm = kate.addChild("right_arm", ModelPartBuilder.create().uv(30, 20).cuboid(-1.0F, 0.0F, -1.0F, 1.0F, 24.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, -49.0F, 0.0F));

		ModelPartData left_arm = kate.addChild("left_arm", ModelPartBuilder.create().uv(26, 20).cuboid(0.0F, 0.0F, -1.0F, 1.0F, 24.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, -49.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(KateEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);

		this.animateMovement(ModAnimations.KATE_WALK, limbSwing, limbSwingAmount, 2, 2.5f);
		this.updateAnimation(entity.idleAnimationState, ModAnimations.KATE_IDLE, ageInTicks, 1);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30, 30);
		headPitch = MathHelper.clamp(headPitch, -25, 45);

		this.head.yaw = headYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		kate.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return kate;
	}
}