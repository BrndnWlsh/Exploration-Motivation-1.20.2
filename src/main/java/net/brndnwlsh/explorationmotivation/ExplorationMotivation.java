package net.brndnwlsh.explorationmotivation;

import net.brndnwlsh.explorationmotivation.block.ModBlocks;
import net.brndnwlsh.explorationmotivation.entity.ModEntities;
import net.brndnwlsh.explorationmotivation.entity.custom.AnuEntity;
import net.brndnwlsh.explorationmotivation.entity.custom.DwarfEntity;
import net.brndnwlsh.explorationmotivation.entity.custom.ElfEntity;
import net.brndnwlsh.explorationmotivation.entity.custom.ElfInteractionHandler;
import net.brndnwlsh.explorationmotivation.item.ModItemGroups;
import net.brndnwlsh.explorationmotivation.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExplorationMotivation implements ModInitializer {
	public static final String MOD_ID = "explorationmotivation";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		FabricDefaultAttributeRegistry.register(ModEntities.ELF, ElfEntity.createElfAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.DWARF, DwarfEntity.createDwarfAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.ANU, AnuEntity.createAnuAttributes());

		ElfInteractionHandler handler = new ElfInteractionHandler();
		handler.register();
	}
}