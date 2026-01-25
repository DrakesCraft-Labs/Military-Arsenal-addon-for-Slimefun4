package com.Chagui68.weaponsaddon.items;

import com.Chagui68.weaponsaddon.WeaponsAddon;
import com.Chagui68.weaponsaddon.items.machines.AmmunitionWorkshop;
import com.Chagui68.weaponsaddon.items.machines.MilitaryCraftingTable;
import com.Chagui68.weaponsaddon.items.machines.MilitaryMachineFabricator;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.NamespacedKey;

public class MilitaryRecipeTypes {

    public static final RecipeType AMMUNITION_WORKSHOP = new RecipeType(
            new NamespacedKey(WeaponsAddon.getInstance(), "ammunition_workshop"),
            AmmunitionWorkshop.AMMUNITION_WORKSHOP
    );

    public static final RecipeType MILITARY_CRAFTING_TABLE = new RecipeType(
            new NamespacedKey(WeaponsAddon.getInstance(), "military_crafting_table"),
            MilitaryCraftingTable.MILITARY_CRAFTING_TABLE
    );

    public static final RecipeType MILITARY_MACHINE_FABRICATOR = new RecipeType(
            new NamespacedKey(WeaponsAddon.getInstance(), "military_machine_fabricator"),
            MilitaryMachineFabricator.MILITARY_MACHINE_FABRICATOR
    );
}
