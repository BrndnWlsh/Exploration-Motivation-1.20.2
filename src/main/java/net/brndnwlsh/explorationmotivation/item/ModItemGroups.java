package net.brndnwlsh.explorationmotivation.item;

import net.brndnwlsh.explorationmotivation.ExplorationMotivation;
import net.brndnwlsh.explorationmotivation.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup ELVEN = Registry.register(Registries.ITEM_GROUP,
            new Identifier(ExplorationMotivation.MOD_ID, "elven"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.elven"))
                    .icon(() -> new ItemStack(ModItems.SAPPHIRE)).entries((displayContext, entries) -> {
                        entries.add(ModItems.SAPPHIRE);
                        entries.add(ModBlocks.SAPPHIRE_BLOCK);
                        entries.add(ModItems.TOTEM_OF_LIFE);
                        entries.add(ModItems.ELVEN_SABRE);
                        entries.add(ModItems.ELF_SPAWN_EGG);
                        entries.add(ModItems.ANU_SPAWN_EGG);

                    }).build());
    public static final ItemGroup RUBY = Registry.register(Registries.ITEM_GROUP,
            new Identifier(ExplorationMotivation.MOD_ID, "ruby"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.ruby"))
                    .icon(() -> new ItemStack(ModItems.RUBY)).entries((displayContext, entries) -> {
                        entries.add(ModItems.RUBY);
                        entries.add(ModBlocks.RUBY_BLOCK);

                    }).build());
    public static final ItemGroup DWARVEN = Registry.register(Registries.ITEM_GROUP,
            new Identifier(ExplorationMotivation.MOD_ID, "dwarven"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.dwarven"))
                    .icon(() -> new ItemStack(ModItems.STEEL)).entries((displayContext, entries) -> {
                        entries.add(ModItems.DWARF_SPAWN_EGG);
                        entries.add(ModItems.STEEL);
                        entries.add(ModBlocks.STEEL_BLOCK);

                    }).build());

    public static void registerItemGroups() {
ExplorationMotivation.LOGGER.info("Registering Item Groups for " + ExplorationMotivation.MOD_ID);
    }
}
