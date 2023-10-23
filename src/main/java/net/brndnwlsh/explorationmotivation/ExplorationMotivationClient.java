package net.brndnwlsh.explorationmotivation;

import net.brndnwlsh.explorationmotivation.entity.ModEntities;
import net.brndnwlsh.explorationmotivation.entity.client.ElfModel;
import net.brndnwlsh.explorationmotivation.entity.client.ElfRenderer;
import net.brndnwlsh.explorationmotivation.entity.client.ModModelLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class ExplorationMotivationClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.ELF, ElfRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ELF, ElfModel::getTexturedModelData);
    }
}
