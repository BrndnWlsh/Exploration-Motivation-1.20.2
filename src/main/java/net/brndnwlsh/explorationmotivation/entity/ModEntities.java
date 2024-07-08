package net.brndnwlsh.explorationmotivation.entity;

import net.brndnwlsh.explorationmotivation.ExplorationMotivation;
import net.brndnwlsh.explorationmotivation.entity.custom.AnuEntity;
import net.brndnwlsh.explorationmotivation.entity.custom.DwarfEntity;
import net.brndnwlsh.explorationmotivation.entity.custom.ElfEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<ElfEntity> ELF = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(ExplorationMotivation.MOD_ID, "elf"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ElfEntity::new)
                    .dimensions(EntityDimensions.fixed(.55f, 1.95f)).build());
    public static final EntityType<AnuEntity> ANU = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(ExplorationMotivation.MOD_ID, "anu"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, AnuEntity::new)
                    .dimensions(EntityDimensions.fixed(.55f, 2.25f)).build());
    public static final EntityType<DwarfEntity> DWARF = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(ExplorationMotivation.MOD_ID, "dwarf"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DwarfEntity::new)
                    .dimensions(EntityDimensions.fixed(.65f, 1.75f)).build());
}
