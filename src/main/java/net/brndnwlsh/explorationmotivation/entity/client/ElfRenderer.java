package net.brndnwlsh.explorationmotivation.entity.client;

import net.brndnwlsh.explorationmotivation.ExplorationMotivation;
import net.brndnwlsh.explorationmotivation.entity.custom.ElfEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class ElfRenderer extends MobEntityRenderer<ElfEntity, ElfModel<ElfEntity>> {
    private static final Identifier TEXTURE = new Identifier(ExplorationMotivation.MOD_ID,
            "textures/entity/elf.png");

    public ElfRenderer(EntityRendererFactory.Context context) {
        super(context, new ElfModel<>(context.getPart(ModModelLayers.ELF)), 0.4f);
    }

    @Override
    public Identifier getTexture(ElfEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(ElfEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(mobEntity.isBaby()){
            matrixStack.scale(0.65f, 0.65f, 0.65f);
        }   else{
            matrixStack.scale(1.3f, 1.3f, 1.3f);
        }

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
