package net.brndnwlsh.explorationmotivation.item;

import net.brndnwlsh.explorationmotivation.ExplorationMotivation;
import net.brndnwlsh.explorationmotivation.entity.ModEntities;
import net.brndnwlsh.explorationmotivation.item.custom.TotemOfLifeItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    public static final Item RUBY = registerItem("ruby", new Item(new FabricItemSettings()));
    public static final Item SAPPHIRE = registerItem("sapphire", new Item(new FabricItemSettings()));

    public static final Item ELF_SPAWN_EGG = registerItem("elf_spawn_egg",
            new SpawnEggItem(ModEntities.ELF, 0x93e34b, 0x234504, new FabricItemSettings()));
    public static final Item DWARF_SPAWN_EGG = registerItem("dwarf_spawn_egg",
            new SpawnEggItem(ModEntities.DWARF, 0x3c377c, 0xff5520, new FabricItemSettings()));

    public static final Item TOTEM_OF_LIFE = registerItem("totem_of_life",
            new TotemOfLifeItem(new FabricItemSettings().maxDamage(64).rarity(Rarity.UNCOMMON)));
    public static final Item ELVEN_SABRE = registerItem("elven_sabre",
        new SwordItem(ModToolMaterial.ELVEN, 3,-2.4f ,new FabricItemSettings()));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(ExplorationMotivation.MOD_ID, name), item);
    }

    public static void registerModItems(){
        ExplorationMotivation.LOGGER.info("Registering Mod Items for " + ExplorationMotivation.MOD_ID);

    }
}
