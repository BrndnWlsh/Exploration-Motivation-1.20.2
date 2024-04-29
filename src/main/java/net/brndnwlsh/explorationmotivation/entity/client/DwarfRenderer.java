package net.brndnwlsh.explorationmotivation.entity.client;

import net.brndnwlsh.explorationmotivation.ExplorationMotivation;
import net.brndnwlsh.explorationmotivation.entity.custom.DwarfEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class DwarfRenderer extends MobEntityRenderer<DwarfEntity, DwarfModel<DwarfEntity>> {
    private static final Identifier TEXTURE = new Identifier(ExplorationMotivation.MOD_ID,
            "textures/entity/dwarf.png");

    public DwarfRenderer(EntityRendererFactory.Context context) {
        super(context, new DwarfModel<>(context.getPart(ModModelLayers.DWARF)), 0.4f);
    }

    @Override
    public Identifier getTexture(DwarfEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(DwarfEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(mobEntity.isBaby()){
            matrixStack.scale(0.65f, 0.65f, 0.65f);
        }   else{
            matrixStack.scale(1.0f, 1.0f, 1.0f);
        }

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
