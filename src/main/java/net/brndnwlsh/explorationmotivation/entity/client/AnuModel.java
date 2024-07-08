package net.brndnwlsh.explorationmotivation.entity.client;

import net.brndnwlsh.explorationmotivation.entity.animation.ModAnimations;
import net.brndnwlsh.explorationmotivation.entity.custom.AnuEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class AnuModel<T extends AnuEntity> extends SinglePartEntityModel<T> {
	private final ModelPart anu;
	private final ModelPart head;
	public AnuModel(ModelPart root) {
		this.anu = root.getChild("anu");
		this.head = anu.getChild("body").getChild("torso").getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData anu = modelPartData.addChild("anu", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 0.0F));

		ModelPartData body = anu.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData torso = body.addChild("torso", ModelPartBuilder.create().uv(18, 0).cuboid(-2.5F, -9.5F, -2.5F, 6.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.5F, 3.5F, 0.5F));

		ModelPartData lumbar = torso.addChild("lumbar", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, 1.0F, -3.0F, 6.0F, 9.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.5F, -2.5F, 0.5F));

		ModelPartData legs = lumbar.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 8.0F, -1.0F));

		ModelPartData right_leg = legs.addChild("right_leg", ModelPartBuilder.create().uv(0, 34).cuboid(-1.0F, 0.0F, -2.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F))
		.uv(29, 11).cuboid(-1.5F, 3.0F, -3.25F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.75F, 2.0F, 0.5F));

		ModelPartData right_shin = right_leg.addChild("right_shin", ModelPartBuilder.create().uv(27, 32).cuboid(-1.0F, 1.0F, 0.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 4.0F, -2.0F));

		ModelPartData left_leg = legs.addChild("left_leg", ModelPartBuilder.create().uv(16, 33).cuboid(-1.0F, 0.0F, -2.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F))
		.uv(29, 11).cuboid(-1.5F, 3.0F, -3.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(1.75F, 2.0F, 0.25F));

		ModelPartData left_shin = left_leg.addChild("left_shin", ModelPartBuilder.create().uv(8, 33).cuboid(-1.0F, 1.0F, 0.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 4.0F, -2.0F));

		ModelPartData head = torso.addChild("head", ModelPartBuilder.create().uv(0, 12).cuboid(-2.4974F, -8.0916F, -1.9407F, 5.0F, 8.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 63).cuboid(-3.4974F, -9.0916F, -2.4407F, 7.0F, 7.0F, 5.0F, new Dilation(0.0F))
		.uv(22, 55).cuboid(-3.5F, -11.0F, -2.75F, 7.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.4974F, -9.4084F, -1.0593F));

		ModelPartData rightwing_r1 = head.addChild("rightwing_r1", ModelPartBuilder.create().uv(29, 57).cuboid(-2.5F, -2.0F, 0.0F, 5.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-3.5F, -11.0F, -0.25F, 0.0F, -1.5708F, 0.0F));

		ModelPartData leftwing_r1 = head.addChild("leftwing_r1", ModelPartBuilder.create().uv(29, 57).cuboid(0.0F, -4.0F, 0.4844F, 5.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, -9.0F, -2.75F, 0.0F, -1.5708F, 0.0F));

		ModelPartData leftear2_r1 = head.addChild("leftear2_r1", ModelPartBuilder.create().uv(14, 12).cuboid(-1.7344F, -1.5F, -0.5F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(34, 16).cuboid(-6.2813F, -1.5F, -0.5F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(24, 22).cuboid(-6.25F, -2.5F, 0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(35, 32).cuboid(-1.75F, -2.5F, 0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.5026F, -3.5916F, -0.4407F, -0.2618F, 0.0F, 0.0F));

		ModelPartData nose_r1 = head.addChild("nose_r1", ModelPartBuilder.create().uv(0, 12).cuboid(-0.5F, -1.5F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0026F, -3.5916F, -2.4407F, -0.3491F, 0.0F, 0.0F));

		ModelPartData right_wing = torso.addChild("right_wing", ModelPartBuilder.create().uv(40, 0).cuboid(-24.5F, -21.0F, 1.0F, 25.0F, 22.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_wing_mid = right_wing.addChild("right_wing_mid", ModelPartBuilder.create().uv(44, 22).cuboid(-25.0F, -13.0F, 0.0F, 25.0F, 26.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-24.5F, -11.0F, 1.0F));

		ModelPartData right_wing_tip = right_wing_mid.addChild("right_wing_tip", ModelPartBuilder.create().uv(72, 79).cuboid(-25.0F, -11.5F, 0.0F, 25.0F, 23.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-25.0F, -4.5F, 0.0F));

		ModelPartData left_wing = torso.addChild("left_wing", ModelPartBuilder.create().uv(40, 0).cuboid(-24.9053F, -21.5F, -0.0783F, 25.0F, 22.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.5F, 1.0F, 0.0F, 3.1416F, 0.0F));

		ModelPartData left_wing_mid = left_wing.addChild("left_wing_mid", ModelPartBuilder.create().uv(44, 22).cuboid(-25.5F, -24.0F, 1.0F, 25.0F, 26.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-24.4053F, -0.5F, -1.0783F));

		ModelPartData left_wing_tip = left_wing_mid.addChild("left_wing_tip", ModelPartBuilder.create().uv(72, 79).cuboid(-25.5F, -23.0F, 1.0F, 25.0F, 23.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-25.0F, -4.0F, 0.0F));

		ModelPartData left_arm = body.addChild("left_arm", ModelPartBuilder.create().uv(13, 22).cuboid(0.0F, -1.5F, -2.5F, 3.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, -3.5F, -0.5F));

		ModelPartData left_forearm = left_arm.addChild("left_forearm", ModelPartBuilder.create().uv(0, 24).cuboid(-0.5F, 1.0F, -3.0F, 2.0F, 7.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, 3.5F, 1.5F));

		ModelPartData right_arm = body.addChild("right_arm", ModelPartBuilder.create().uv(18, 11).cuboid(-3.0F, -1.5F, -2.5F, 3.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -3.5F, -0.5F));

		ModelPartData right_forearm = right_arm.addChild("right_forearm", ModelPartBuilder.create().uv(29, 22).cuboid(-1.5F, 1.0F, -3.0F, 2.0F, 7.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 3.5F, 1.5F));

		ModelPartData sword = right_forearm.addChild("sword", ModelPartBuilder.create().uv(26, 43).cuboid(-1.0F, 5.5F, -3.5F, 1.0F, 2.0F, 4.0F, new Dilation(0.0F))
		.uv(32, 45).cuboid(-1.0F, 7.5F, -3.5F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(14, 33).cuboid(-1.0F, 5.5F, -31.5F, 1.0F, 2.0F, 28.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData bladetip_r1 = sword.addChild("bladetip_r1", ModelPartBuilder.create().uv(12, 51).cuboid(-0.5F, -2.0F, -11.0F, 1.0F, 2.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 7.205F, -37.6443F, -0.4363F, 0.0F, 0.0F));

		ModelPartData blademiddle_r1 = sword.addChild("blademiddle_r1", ModelPartBuilder.create().uv(0, 48).cuboid(-0.5F, -2.0F, -9.0F, 1.0F, 2.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 7.5F, -31.5F, -0.1745F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(AnuEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);

		this.animateMovement(ModAnimations.ANU_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.updateAnimation(entity.idleAnimationState, ModAnimations.ANU_IDLE, ageInTicks, 1f);
		this.updateAnimation(entity.lungeAnimationState1, ModAnimations.ANU_LUNGE1, ageInTicks, 1f);
		this.updateAnimation(entity.lungeAnimationState2, ModAnimations.ANU_LUNGE2, ageInTicks, 1f);
		this.updateAnimation(entity.basicAttackAnimationState1, ModAnimations.ANU_ATTACK1, ageInTicks, 1f);
		this.updateAnimation(entity.basicAttackAnimationState2, ModAnimations.ANU_ATTACK2, ageInTicks, 1f);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
		headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

		this.head.yaw = headYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		anu.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return anu;
	}
}