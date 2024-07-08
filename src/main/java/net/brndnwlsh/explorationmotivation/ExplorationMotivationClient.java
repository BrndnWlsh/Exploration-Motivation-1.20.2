package net.brndnwlsh.explorationmotivation;

import net.brndnwlsh.explorationmotivation.entity.ModEntities;
import net.brndnwlsh.explorationmotivation.entity.client.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class ExplorationMotivationClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.ELF, ElfRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ELF, ElfModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.DWARF, DwarfRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.DWARF, DwarfModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.ANU, AnuRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ANU, AnuModel::getTexturedModelData);
    }
}
