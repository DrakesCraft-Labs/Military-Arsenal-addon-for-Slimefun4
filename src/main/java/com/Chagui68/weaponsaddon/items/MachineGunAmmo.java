package com.Chagui68.weaponsaddon.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class MachineGunAmmo {

    public static final SlimefunItemStack MACHINE_GUN_AMMO = new SlimefunItemStack(
            "MACHINE_GUN_AMMO",
            Material.IRON_NUGGET,
            "&eMachine Gun Bullets",
            "",
            "&7High-velocity ammunition",
            "&7for automatic weapons",
            "",
            "&6Damage: &c5 HP per shot",
            "&6Stack: &764"
    );

    public static void register(SlimefunAddon addon, ItemGroup category) {
        SlimefunItem ammo = new SlimefunItem(category, MACHINE_GUN_AMMO, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                null, SlimefunItems.COPPER_INGOT, null,
                SlimefunItems.LEAD_INGOT, new ItemStack(Material.GUNPOWDER), SlimefunItems.LEAD_INGOT,
                null, SlimefunItems.STEEL_INGOT, null
        });


        ItemStack output = MACHINE_GUN_AMMO.clone();
        output.setAmount(5);
        ammo.setRecipeOutput(output);

        ammo.register(addon);
    }
}
