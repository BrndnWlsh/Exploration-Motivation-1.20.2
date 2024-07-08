package net.brndnwlsh.explorationmotivation.entity.client;

import net.brndnwlsh.explorationmotivation.ExplorationMotivation;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer ELF =
            new EntityModelLayer(new Identifier(ExplorationMotivation.MOD_ID, "elf"), "main");
    public static final EntityModelLayer ANU =
            new EntityModelLayer(new Identifier(ExplorationMotivation.MOD_ID, "anu"), "main");
    public static final EntityModelLayer DWARF =
            new EntityModelLayer(new Identifier(ExplorationMotivation.MOD_ID, "dwarf"), "main");
}
