package net.brndnwlsh.explorationmotivation.entity.client;

import net.brndnwlsh.explorationmotivation.entity.animation.ModAnimations;
import net.brndnwlsh.explorationmotivation.entity.custom.DwarfEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class DwarfModel<T extends DwarfEntity> extends SinglePartEntityModel<T> {
	private final ModelPart dwarf;
	private final ModelPart head;

	public DwarfModel(ModelPart root) {
		this.dwarf = root.getChild("dwarf");
		this.head = dwarf.getChild("body").getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData dwarf = modelPartData.addChild("dwarf", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData body = dwarf.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -20.0F, -3.0F, 10.0F, 15.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 22).mirrored().cuboid(-5.0F, -3.659F, -1.7844F, 10.0F, 8.0F, 7.0F, new Dilation(0.0F)).mirrored(false)
		.uv(27, 2).cuboid(1.0F, -2.659F, -2.5344F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(27, 2).mirrored().cuboid(-4.0F, -2.659F, -2.5344F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, -24.341F, -1.2156F));

		ModelPartData cube_r1 = head.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-0.25F, -1.5F, -0.5F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(0, 0).cuboid(9.75F, -1.5F, -0.5F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-5.25F, 0.841F, 1.4656F, -0.2618F, 0.0F, 0.0F));

		ModelPartData cube_r2 = head.addChild("cube_r2", ModelPartBuilder.create().uv(16, 37).cuboid(-1.0F, -2.5F, -0.75F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.841F, -1.7844F, -0.1745F, 0.0F, 0.0F));

		ModelPartData beard = head.addChild("beard", ModelPartBuilder.create().uv(0, 5).mirrored().cuboid(2.0F, -20.0F, -4.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
		.uv(0, 5).cuboid(-5.0F, -20.0F, -4.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(27, 26).cuboid(-5.0F, -19.0F, -4.0F, 10.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(34, 10).cuboid(-3.0F, -17.0F, -4.0F, 6.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 22).cuboid(-1.0F, -15.0F, -4.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 22.341F, 1.2156F));

		ModelPartData leftleg = body.addChild("leftleg", ModelPartBuilder.create().uv(34, 0).cuboid(-2.0F, -2.75F, -2.25F, 4.0F, 3.0F, 5.0F, new Dilation(0.0F))
		.uv(28, 31).mirrored().cuboid(-2.0F, 0.25F, -3.25F, 4.0F, 2.0F, 6.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(2.9375F, -2.25F, 0.25F));

		ModelPartData leftarm = body.addChild("leftarm", ModelPartBuilder.create().uv(27, 15).mirrored().cuboid(-0.553F, -2.1983F, -3.5F, 5.0F, 4.0F, 7.0F, new Dilation(0.0F)).mirrored(false)
		.uv(0, 37).cuboid(0.447F, 1.8017F, -2.5F, 3.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(5.053F, -15.8017F, 0.5F));

		ModelPartData rightarm = body.addChild("rightarm", ModelPartBuilder.create().uv(27, 15).cuboid(-4.447F, -2.1983F, -3.5F, 5.0F, 4.0F, 7.0F, new Dilation(0.0F))
		.uv(0, 37).mirrored().cuboid(-3.447F, 1.8017F, -2.5F, 3.0F, 5.0F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-5.053F, -15.8017F, 0.5F));

		ModelPartData hammer = rightarm.addChild("hammer", ModelPartBuilder.create().uv(5, 43).cuboid(-0.5F, -1.0F, -8.5F, 1.0F, 2.0F, 19.0F, new Dilation(0.0F))
		.uv(17, 51).cuboid(-1.0F, -1.5F, 10.5F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(36, 43).cuboid(-2.5F, -5.5F, -15.5F, 5.0F, 11.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.947F, 4.8017F, -6.5F));

		ModelPartData middle = hammer.addChild("middle", ModelPartBuilder.create().uv(8, 58).cuboid(-4.75F, -2.5F, -2.0F, 7.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(8, 58).cuboid(-4.75F, -2.5F, 6.0F, 7.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(40, 39).mirrored().cuboid(1.25F, -1.5F, 1.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
		.uv(45, 38).mirrored().cuboid(1.25F, -3.5F, 1.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
		.uv(35, 41).mirrored().cuboid(1.25F, -2.5F, -1.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
		.uv(35, 45).mirrored().cuboid(1.25F, -2.5F, 3.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
		.uv(35, 45).cuboid(-4.75F, -2.5F, 3.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(35, 41).cuboid(-4.75F, -2.5F, -1.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(45, 38).cuboid(-4.75F, -3.5F, 1.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(40, 39).cuboid(-4.75F, -1.5F, 1.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(1.25F, 2.0F, -14.5F));

		ModelPartData back = hammer.addChild("back", ModelPartBuilder.create().uv(8, 60).cuboid(-4.75F, -6.5F, 6.0F, 7.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(8, 48).cuboid(1.25F, -6.5F, -1.0F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F))
		.uv(26, 42).cuboid(-4.75F, -6.5F, -1.0F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F))
		.uv(8, 60).cuboid(-4.75F, -6.5F, -2.0F, 7.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(1.25F, 2.0F, -14.5F));

		ModelPartData front = hammer.addChild("front", ModelPartBuilder.create().uv(8, 56).cuboid(-4.75F, -6.5F, 6.0F, 7.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(8, 48).cuboid(1.25F, -6.5F, -1.0F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F))
		.uv(26, 42).cuboid(-4.75F, -6.5F, -1.0F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F))
		.uv(8, 56).cuboid(-4.75F, -6.5F, -2.0F, 7.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(1.25F, 10.0F, -14.5F));

		ModelPartData rightleg = body.addChild("rightleg", ModelPartBuilder.create().uv(28, 31).cuboid(-2.0F, 0.25F, -3.25F, 4.0F, 2.0F, 6.0F, new Dilation(0.0F))
		.uv(34, 0).mirrored().cuboid(-2.0F, -2.75F, -2.25F, 4.0F, 3.0F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-2.9375F, -2.25F, 0.25F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(DwarfEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);

		this.animateMovement(ModAnimations.DWARF_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.updateAnimation(entity.idleAnimationState, ModAnimations.DWARF_IDLE, ageInTicks, 1f);
		this.updateAnimation(entity.attackAnimationState, ModAnimations.DWARF_ATTACK, ageInTicks, 1f);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
		headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

		this.head.yaw = headYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		dwarf.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return dwarf;
	}
}