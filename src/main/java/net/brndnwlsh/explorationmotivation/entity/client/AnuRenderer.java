package net.brndnwlsh.explorationmotivation.entity.client;

import net.brndnwlsh.explorationmotivation.ExplorationMotivation;
import net.brndnwlsh.explorationmotivation.entity.custom.AnuEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class AnuRenderer extends MobEntityRenderer<AnuEntity, AnuModel<AnuEntity>> {
    private static final Identifier TEXTURE = new Identifier(ExplorationMotivation.MOD_ID,
            "textures/entity/anu.png");

    public AnuRenderer(EntityRendererFactory.Context context) {
        super(context, new AnuModel<>(context.getPart(ModModelLayers.ANU)), 0.4f);
    }

    @Override
    public Identifier getTexture(AnuEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(AnuEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1.3f, 1.3f, 1.3f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
