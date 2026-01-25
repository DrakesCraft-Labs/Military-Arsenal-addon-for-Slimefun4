package com.Chagui68.weaponsaddon.items.components;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class MilitaryComponents {

    public static final SlimefunItemStack MILITARY_CIRCUIT = new SlimefunItemStack(
            "MILITARY_CIRCUIT",
            Material.REDSTONE_BLOCK,
            "&cMilitary Circuit",
            "",
            "&7Advanced military-grade circuit",
            "&7Used in weapon systems",
            "",
            "&eLevel: &61"
    );

    public static final SlimefunItemStack GUIDANCE_CHIP = new SlimefunItemStack(
            "GUIDANCE_CHIP",
            Material.PRISMARINE_SHARD,
            "&bGuidance Chip",
            "",
            "&7GPS guidance processor",
            "&7For precision targeting",
            "",
            "&eLevel: &62"
    );

    public static final SlimefunItemStack TARGETING_SYSTEM = new SlimefunItemStack(
            "TARGETING_SYSTEM",
            Material.COMPASS,
            "&6Targeting System",
            "",
            "&7Advanced aim assistance module",
            "&7Improves weapon accuracy",
            "",
            "&eLevel: &62"
    );

    public static final SlimefunItemStack REINFORCED_FRAME = new SlimefunItemStack(
            "REINFORCED_FRAME",
            Material.IRON_BLOCK,
            "&8Reinforced Frame",
            "",
            "&7Military-grade structural plating",
            "&7Provides extra durability",
            "",
            "&eLevel: &62"
    );

    public static final SlimefunItemStack QUANTUM_PROCESSOR = new SlimefunItemStack(
            "QUANTUM_PROCESSOR",
            Material.NETHER_STAR,
            "&dQuantum Processor",
            "",
            "&7Next-generation computing core",
            "&7Enables advanced weapon systems",
            "",
            "&eLevel: &63"
    );

    public static final SlimefunItemStack EXPLOSIVE_CORE = new SlimefunItemStack(
            "EXPLOSIVE_CORE",
            Material.TNT,
            "&4Explosive Core",
            "",
            "&7Stabilized explosive payload",
            "&7Highly volatile",
            "",
            "&eLevel: &63"
    );

    public static void register(SlimefunAddon addon, ItemGroup category) {
        new SlimefunItem(category, MILITARY_CIRCUIT, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.REDSTONE_ALLOY, SlimefunItems.GOLD_24K, SlimefunItems.REDSTONE_ALLOY,
                SlimefunItems.COPPER_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.COPPER_INGOT,
                SlimefunItems.REDSTONE_ALLOY, SlimefunItems.POWER_CRYSTAL, SlimefunItems.REDSTONE_ALLOY
        }).register(addon);

        new SlimefunItem(category, GUIDANCE_CHIP, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.SYNTHETIC_SAPPHIRE, MILITARY_CIRCUIT, SlimefunItems.SYNTHETIC_SAPPHIRE,
                SlimefunItems.COPPER_WIRE, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.COPPER_WIRE,
                SlimefunItems.SYNTHETIC_EMERALD, MILITARY_CIRCUIT, SlimefunItems.SYNTHETIC_EMERALD
        }).register(addon);

        new SlimefunItem(category, TARGETING_SYSTEM, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.SYNTHETIC_DIAMOND, GUIDANCE_CHIP, SlimefunItems.SYNTHETIC_DIAMOND,
                MILITARY_CIRCUIT, new ItemStack(Material.OBSERVER), MILITARY_CIRCUIT,
                SlimefunItems.BILLON_INGOT, SlimefunItems.GPS_TRANSMITTER, SlimefunItems.BILLON_INGOT
        }).register(addon);

        new SlimefunItem(category, REINFORCED_FRAME, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.STEEL_PLATE, SlimefunItems.REINFORCED_ALLOY_INGOT,
                SlimefunItems.STEEL_PLATE, SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.STEEL_PLATE,
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.STEEL_PLATE, SlimefunItems.REINFORCED_ALLOY_INGOT
        }).register(addon);

        new SlimefunItem(category, QUANTUM_PROCESSOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.ENERGY_REGULATOR, GUIDANCE_CHIP, SlimefunItems.ENERGY_REGULATOR,
                MILITARY_CIRCUIT, new ItemStack(Material.NETHER_STAR), MILITARY_CIRCUIT,
                SlimefunItems.CARBONADO, GUIDANCE_CHIP, SlimefunItems.CARBONADO
        }).register(addon);

        new SlimefunItem(category, EXPLOSIVE_CORE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                new ItemStack(Material.TNT), MILITARY_CIRCUIT, new ItemStack(Material.TNT),
                GUIDANCE_CHIP, SlimefunItems.URANIUM, GUIDANCE_CHIP,
                new ItemStack(Material.TNT), TARGETING_SYSTEM, new ItemStack(Material.TNT)
        }).register(addon);
    }
}
