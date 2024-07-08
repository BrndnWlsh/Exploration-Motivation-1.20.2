package net.brndnwlsh.explorationmotivation.datagen;

import net.brndnwlsh.explorationmotivation.block.ModBlocks;
import net.brndnwlsh.explorationmotivation.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RUBY_BLOCK);
    blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SAPPHIRE_BLOCK);
    blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.STEEL_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
    itemModelGenerator.register(ModItems.RUBY, Models.GENERATED);
    itemModelGenerator.register(ModItems.SAPPHIRE, Models.GENERATED);
    itemModelGenerator.register(ModItems.STEEL, Models.GENERATED);

    itemModelGenerator.register(ModItems.TOTEM_OF_LIFE, Models.GENERATED);
    itemModelGenerator.register(ModItems.ELVEN_SABRE, Models.HANDHELD);

    itemModelGenerator.register(ModItems.ELF_SPAWN_EGG,
            new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));
    itemModelGenerator.register(ModItems.DWARF_SPAWN_EGG,
            new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));
    itemModelGenerator.register(ModItems.ANU_SPAWN_EGG,
            new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));
    }
}
