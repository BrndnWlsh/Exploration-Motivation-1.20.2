package net.brndnwlsh.explorationmotivation.entity.client;

import net.brndnwlsh.explorationmotivation.entity.animation.ModAnimations;
import net.brndnwlsh.explorationmotivation.entity.custom.ElfEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ElfModel<T extends ElfEntity> extends SinglePartEntityModel<T> {
	private final ModelPart elf;
	private final ModelPart head;

	public ElfModel(ModelPart root) {
		this.elf = root.getChild("elf");
		this.head = elf.getChild("body").getChild("torso").getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData elf = modelPartData.addChild("elf", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 7.0F, 0.0F));

		ModelPartData body = elf.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(3.5F, -9.5F, -1.0F));

		ModelPartData torso = body.addChild("torso", ModelPartBuilder.create().uv(18, 0).cuboid(-2.5F, -9.5F, -2.5F, 6.0F, 7.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 13.0F, 1.5F));

		ModelPartData lumbar = torso.addChild("lumbar", ModelPartBuilder.create().uv(0, 12).cuboid(-3.0F, 0.0F, -3.0F, 6.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.5F, -2.5F, 0.5F));

		ModelPartData legs = lumbar.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 8.0F, -1.0F));

		ModelPartData right_leg = legs.addChild("right_leg", ModelPartBuilder.create().uv(20, 30).cuboid(-1.0F, 0.0F, -2.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.75F, 0.0F, 0.5F));

		ModelPartData shin2 = right_leg.addChild("shin2", ModelPartBuilder.create().uv(26, 26).cuboid(-1.0F, 0.0F, 0.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 4.0F, -2.0F));

		ModelPartData left_leg = legs.addChild("left_leg", ModelPartBuilder.create().uv(28, 16).cuboid(-1.0F, 0.0F, -2.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(1.75F, 0.0F, 0.25F));

		ModelPartData shin = left_leg.addChild("shin", ModelPartBuilder.create().uv(28, 10).cuboid(-1.0F, 0.0F, 0.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 4.0F, -2.0F));

		ModelPartData head = torso.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-2.4974F, -8.0916F, -1.9407F, 5.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.4974F, -9.4084F, -1.0593F));

		ModelPartData leftear2_r1 = head.addChild("leftear2_r1", ModelPartBuilder.create().uv(28, 22).cuboid(-1.7344F, -1.5F, -0.5F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 31).cuboid(-6.2813F, -1.5F, -0.5F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(14, 0).cuboid(-6.25F, -2.5F, 0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(6, 31).cuboid(-1.75F, -2.5F, 0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.5026F, -3.5916F, -0.4407F, -0.2618F, 0.0F, 0.0F));

		ModelPartData nose_r1 = head.addChild("nose_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-0.5F, -1.5F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0026F, -3.5916F, -2.4407F, -0.3491F, 0.0F, 0.0F));

		ModelPartData left_arm = body.addChild("left_arm", ModelPartBuilder.create().uv(10, 25).cuboid(0.0F, -1.5F, -1.5F, 2.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.5F, 6.0F, 0.5F));

		ModelPartData left_forearm = left_arm.addChild("left_forearm", ModelPartBuilder.create().uv(18, 10).cuboid(-1.0F, 0.0F, -3.0F, 2.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, 3.5F, 1.5F));

		ModelPartData right_arm = body.addChild("right_arm", ModelPartBuilder.create().uv(0, 23).cuboid(-2.0F, -1.5F, -1.5F, 2.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.5F, 6.0F, 0.5F));

		ModelPartData right_forearm = right_arm.addChild("right_forearm", ModelPartBuilder.create().uv(18, 19).cuboid(-1.0F, 0.0F, -3.0F, 2.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 3.5F, 1.5F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(ElfEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);

		this.animateMovement(ModAnimations.ELF_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.updateAnimation(entity.idleAnimationState, ModAnimations.ELF_IDLE, ageInTicks, 1f);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
		headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

		this.head.yaw = headYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		elf.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
	@Override
	public ModelPart getPart(){
		return elf;
	}
}