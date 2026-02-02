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

    // ========== TIER 1: BASIC COMPONENTS ==========
    public static final SlimefunItemStack BASIC_CIRCUIT = new SlimefunItemStack(
            "BASIC_CIRCUIT",
            Material.REDSTONE_TORCH,
            "&6⚡ &eBasic Circuit",
            "",
            "&7Foundation for all electronics",
            "&7Used in basic machinery",
            "",
            "&8⇨ Tier 1 Component"
    );

    public static final SlimefunItemStack STEEL_FRAME = new SlimefunItemStack(
            "STEEL_FRAME",
            Material.IRON_BARS,
            "&7⬚ &fSteel Frame",
            "",
            "&7Basic structural component",
            "&7Provides moderate strength",
            "",
            "&8⇨ Tier 1 Component"
    );

    public static final SlimefunItemStack COPPER_COIL = new SlimefunItemStack(
            "COPPER_COIL",
            Material.LIGHTNING_ROD,
            "&6◉ &eCopper Coil",
            "",
            "&7Electromagnetic component",
            "&7For energy systems",
            "",
            "&8⇨ Tier 1 Component"
    );

    public static final SlimefunItemStack MECHANICAL_PARTS = new SlimefunItemStack(
            "MECHANICAL_PARTS",
            Material.REPEATER,
            "&7⚙ &fMechanical Parts",
            "",
            "&7Basic gears and springs",
            "&7For simple mechanisms",
            "",
            "&8⇨ Tier 1 Component"
    );

    // ========== TIER 2: INTERMEDIATE COMPONENTS ==========
    public static final SlimefunItemStack ADVANCED_CIRCUIT = new SlimefunItemStack(
            "ADVANCED_CIRCUIT",
            Material.COMPARATOR,
            "&6⚡ &eAdvanced Circuit",
            "",
            "&7Enhanced electronic board",
            "&7Handles complex operations",
            "",
            "&8⇨ Tier 2 Component"
    );

    public static final SlimefunItemStack REINFORCED_PLATING = new SlimefunItemStack(
            "REINFORCED_PLATING",
            Material.NETHERITE_SCRAP,
            "&8▩ &7Reinforced Plating",
            "",
            "&7Heavy-duty armor plates",
            "&7Maximum protection",
            "",
            "&8⇨ Tier 2 Component"
    );

    public static final SlimefunItemStack SERVO_MOTOR = new SlimefunItemStack(
            "SERVO_MOTOR",
            Material.PISTON,
            "&b⚙ &3Servo Motor",
            "",
            "&7Precision motor unit",
            "&7For controlled movement",
            "",
            "&8⇨ Tier 2 Component"
    );

    public static final SlimefunItemStack POWER_CELL = new SlimefunItemStack(
            "POWER_CELL",
            Material.REPEATING_COMMAND_BLOCK,
            "&e⚡ &6Power Cell",
            "",
            "&7Compact energy storage",
            "&7Stores electrical power",
            "",
            "&8⇨ Tier 2 Component"
    );

    // ========== TIER 3: SPECIALIZED WEAPON COMPONENTS ==========
    public static final SlimefunItemStack WEAPON_BARREL = new SlimefunItemStack(
            "WEAPON_BARREL",
            Material.END_ROD,
            "&c▬ &4Weapon Barrel",
            "",
            "&7Precision rifled barrel",
            "&7For projectile weapons",
            "",
            "&8⇨ Tier 3 Weapon Part"
    );

    public static final SlimefunItemStack TRIGGER_MECHANISM = new SlimefunItemStack(
            "TRIGGER_MECHANISM",
            Material.TRIPWIRE_HOOK,
            "&e⚲ &6Trigger Mechanism",
            "",
            "&7Hair-trigger firing system",
            "&7Ultra-responsive activation",
            "",
            "&8⇨ Tier 3 Weapon Part"
    );

    public static final SlimefunItemStack STABILIZER_UNIT = new SlimefunItemStack(
            "STABILIZER_UNIT",
            Material.COMPASS,
            "&b◎ &3Stabilizer Unit",
            "",
            "&7Gyroscopic stabilization",
            "&7Reduces weapon recoil",
            "",
            "&8⇨ Tier 3 Weapon Part"
    );

    public static final SlimefunItemStack TARGETING_SYSTEM = new SlimefunItemStack(
            "TARGETING_SYSTEM",
            Material.OBSERVER,
            "&c◉ &4Targeting System",
            "",
            "&7Advanced tracking optics",
            "&7Auto-aim technology",
            "",
            "&8⇨ Tier 3 Weapon Part"
    );

    public static final SlimefunItemStack RADAR_MODULE = new SlimefunItemStack(
            "RADAR_MODULE",
            Material.SCULK_SENSOR,
            "&a⦿ &2Radar Module",
            "",
            "&7Enemy detection system",
            "&7Scans surroundings",
            "",
            "&8⇨ Tier 3 Weapon Part"
    );

    // ========== TIER 3: ADVANCED MACHINERY COMPONENTS ==========
    public static final SlimefunItemStack REINFORCED_FRAME = new SlimefunItemStack(
            "REINFORCED_FRAME",
            Material.LODESTONE,
            "&8▦ &7Reinforced Frame",
            "",
            "&7Ultra-strong chassis",
            "&7For heavy machinery",
            "",
            "&8⇨ Tier 3 Machine Part"
    );

    public static final SlimefunItemStack POWER_CORE = new SlimefunItemStack(
            "POWER_CORE",
            Material.BEACON,
            "&e⬢ &6Power Core",
            "",
            "&7High-capacity reactor",
            "&7Infinite power output",
            "",
            "&8⇨ Tier 3 Machine Part"
    );

    public static final SlimefunItemStack MILITARY_CIRCUIT = new SlimefunItemStack(
            "MILITARY_CIRCUIT",
            Material.CALIBRATED_SCULK_SENSOR,
            "&c⚡ &4Military Circuit",
            "",
            "&7Military-grade processor",
            "&7Combat system control",
            "",
            "&8⇨ Tier 3 Machine Part"
    );

    public static final SlimefunItemStack HYDRAULIC_SYSTEM = new SlimefunItemStack(
            "HYDRAULIC_SYSTEM",
            Material.DISPENSER,
            "&9⚒ &1Hydraulic System",
            "",
            "&7High-pressure actuators",
            "&7Extreme force output",
            "",
            "&8⇨ Tier 3 Machine Part"
    );

    public static final SlimefunItemStack COOLANT_SYSTEM = new SlimefunItemStack(
            "COOLANT_SYSTEM",
            Material.CAULDRON,
            "&b◈ &3Coolant System",
            "",
            "&7Liquid nitrogen cooling",
            "&7Prevents overheating",
            "",
            "&8⇨ Tier 3 Machine Part"
    );

    // ========== TIER 4: ULTIMATE COMPONENTS ==========
    public static final SlimefunItemStack QUANTUM_PROCESSOR = new SlimefunItemStack(
            "QUANTUM_PROCESSOR",
            Material.END_CRYSTAL,
            "&d⬡ &5Quantum Processor",
            "",
            "&7Quantum computing core",
            "&7Infinite calculations",
            "",
            "&8⇨ Tier 4 Ultimate"
    );

    public static final SlimefunItemStack ENERGY_MATRIX = new SlimefunItemStack(
            "ENERGY_MATRIX",
            Material.CONDUIT,
            "&e⬢ &6Energy Matrix",
            "",
            "&7Dimensional energy storage",
            "&7Stores unlimited power",
            "",
            "&8⇨ Tier 4 Ultimate"
    );

    public static final SlimefunItemStack EXPLOSIVE_CORE = new SlimefunItemStack(
            "EXPLOSIVE_CORE",
            Material.TNT,
            "&c☢ &4Explosive Core",
            "",
            "&7Concentrated TNT charge",
            "&7Maximum destruction",
            "",
            "&8⇨ Tier 4 Ultimate"
    );

    // ========== REGISTRATION METHOD ==========
    public static void register(SlimefunAddon addon, ItemGroup category) {
        // TIER 1: BASIC COMPONENTS
        new SlimefunItem(category, BASIC_CIRCUIT, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                new ItemStack(Material.REDSTONE), new ItemStack(Material.COPPER_INGOT), new ItemStack(Material.REDSTONE),
                new ItemStack(Material.COPPER_INGOT), new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.COPPER_INGOT),
                new ItemStack(Material.REDSTONE), new ItemStack(Material.COPPER_INGOT), new ItemStack(Material.REDSTONE)
        }).register(addon);

        new SlimefunItem(category, STEEL_FRAME, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.STEEL_INGOT, null, SlimefunItems.STEEL_INGOT,
                null, SlimefunItems.STEEL_INGOT, null,
                SlimefunItems.STEEL_INGOT, null, SlimefunItems.STEEL_INGOT
        }).register(addon);

        new SlimefunItem(category, COPPER_COIL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                null, new ItemStack(Material.COPPER_INGOT), null,
                new ItemStack(Material.COPPER_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.COPPER_INGOT),
                null, new ItemStack(Material.COPPER_INGOT), null
        }).register(addon);

        new SlimefunItem(category, MECHANICAL_PARTS, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                new ItemStack(Material.IRON_NUGGET), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_NUGGET),
                new ItemStack(Material.IRON_INGOT), new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.IRON_INGOT),
                new ItemStack(Material.IRON_NUGGET), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_NUGGET)
        }).register(addon);

        // TIER 2: INTERMEDIATE COMPONENTS
        new SlimefunItem(category, ADVANCED_CIRCUIT, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                BASIC_CIRCUIT, SlimefunItems.ELECTRO_MAGNET, BASIC_CIRCUIT,
                SlimefunItems.ELECTRO_MAGNET, new ItemStack(Material.REDSTONE_BLOCK), SlimefunItems.ELECTRO_MAGNET,
                BASIC_CIRCUIT, SlimefunItems.ELECTRO_MAGNET, BASIC_CIRCUIT
        }).register(addon);

        new SlimefunItem(category, REINFORCED_PLATING, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.STEEL_PLATE, SlimefunItems.HARDENED_METAL_INGOT,
                SlimefunItems.STEEL_PLATE, new ItemStack(Material.NETHERITE_INGOT), SlimefunItems.STEEL_PLATE,
                SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.STEEL_PLATE, SlimefunItems.HARDENED_METAL_INGOT
        }).register(addon);

        new SlimefunItem(category, SERVO_MOTOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                COPPER_COIL, MECHANICAL_PARTS, COPPER_COIL,
                MECHANICAL_PARTS, SlimefunItems.ELECTRIC_MOTOR, MECHANICAL_PARTS,
                COPPER_COIL, MECHANICAL_PARTS, COPPER_COIL
        }).register(addon);

        new SlimefunItem(category, POWER_CELL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.BATTERY, COPPER_COIL, SlimefunItems.BATTERY,
                COPPER_COIL, new ItemStack(Material.REDSTONE_BLOCK), COPPER_COIL,
                SlimefunItems.BATTERY, COPPER_COIL, SlimefunItems.BATTERY
        }).register(addon);

        // TIER 3: WEAPON COMPONENTS
        new SlimefunItem(category, WEAPON_BARREL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                null, SlimefunItems.HARDENED_METAL_INGOT, null,
                null, SlimefunItems.HARDENED_METAL_INGOT, null,
                null, SlimefunItems.HARDENED_METAL_INGOT, null
        }).register(addon);

        new SlimefunItem(category, TRIGGER_MECHANISM, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                null, MECHANICAL_PARTS, null,
                MECHANICAL_PARTS, ADVANCED_CIRCUIT, MECHANICAL_PARTS,
                null, new ItemStack(Material.GOLD_INGOT), null
        }).register(addon);

        new SlimefunItem(category, STABILIZER_UNIT, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SERVO_MOTOR, ADVANCED_CIRCUIT, SERVO_MOTOR,
                STEEL_FRAME, new ItemStack(Material.COMPASS), STEEL_FRAME,
                SERVO_MOTOR, ADVANCED_CIRCUIT, SERVO_MOTOR
        }).register(addon);

        new SlimefunItem(category, TARGETING_SYSTEM, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                ADVANCED_CIRCUIT, new ItemStack(Material.ENDER_EYE), ADVANCED_CIRCUIT,
                new ItemStack(Material.OBSERVER), POWER_CELL, new ItemStack(Material.OBSERVER),
                ADVANCED_CIRCUIT, new ItemStack(Material.ENDER_PEARL), ADVANCED_CIRCUIT
        }).register(addon);

        new SlimefunItem(category, RADAR_MODULE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                COPPER_COIL, ADVANCED_CIRCUIT, COPPER_COIL,
                ADVANCED_CIRCUIT, new ItemStack(Material.SCULK_SENSOR), ADVANCED_CIRCUIT,
                COPPER_COIL, POWER_CELL, COPPER_COIL
        }).register(addon);

        // TIER 3: MACHINE COMPONENTS
        new SlimefunItem(category, REINFORCED_FRAME, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                REINFORCED_PLATING, STEEL_FRAME, REINFORCED_PLATING,
                STEEL_FRAME, new ItemStack(Material.NETHERITE_BLOCK), STEEL_FRAME,
                REINFORCED_PLATING, STEEL_FRAME, REINFORCED_PLATING
        }).register(addon);

        new SlimefunItem(category, POWER_CORE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                POWER_CELL, COPPER_COIL, POWER_CELL,
                COPPER_COIL, new ItemStack(Material.NETHER_STAR), COPPER_COIL,
                POWER_CELL, COPPER_COIL, POWER_CELL
        }).register(addon);

        new SlimefunItem(category, MILITARY_CIRCUIT, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                ADVANCED_CIRCUIT, SlimefunItems.BLISTERING_INGOT, ADVANCED_CIRCUIT,
                SlimefunItems.REINFORCED_ALLOY_INGOT, POWER_CORE, SlimefunItems.REINFORCED_ALLOY_INGOT,
                ADVANCED_CIRCUIT, SlimefunItems.BLISTERING_INGOT, ADVANCED_CIRCUIT
        }).register(addon);

        new SlimefunItem(category, HYDRAULIC_SYSTEM, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SERVO_MOTOR, new ItemStack(Material.PISTON), SERVO_MOTOR,
                new ItemStack(Material.WATER_BUCKET), POWER_CELL, new ItemStack(Material.WATER_BUCKET),
                SERVO_MOTOR, new ItemStack(Material.STICKY_PISTON), SERVO_MOTOR
        }).register(addon);

        new SlimefunItem(category, COOLANT_SYSTEM, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                new ItemStack(Material.POWDER_SNOW_BUCKET), COPPER_COIL, new ItemStack(Material.POWDER_SNOW_BUCKET),
                new ItemStack(Material.ICE), new ItemStack(Material.CAULDRON), new ItemStack(Material.ICE),
                new ItemStack(Material.PACKED_ICE), SERVO_MOTOR, new ItemStack(Material.PACKED_ICE)
        }).register(addon);

        // TIER 4: ULTIMATE COMPONENTS
        new SlimefunItem(category, QUANTUM_PROCESSOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                MILITARY_CIRCUIT, new ItemStack(Material.END_CRYSTAL), MILITARY_CIRCUIT,
                new ItemStack(Material.ENDER_EYE), POWER_CORE, new ItemStack(Material.ENDER_EYE),
                MILITARY_CIRCUIT, new ItemStack(Material.NETHER_STAR), MILITARY_CIRCUIT
        }).register(addon);

        new SlimefunItem(category, ENERGY_MATRIX, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                POWER_CORE, new ItemStack(Material.CONDUIT), POWER_CORE,
                new ItemStack(Material.CONDUIT), new ItemStack(Material.BEACON), new ItemStack(Material.CONDUIT),
                POWER_CORE, new ItemStack(Material.CONDUIT), POWER_CORE
        }).register(addon);

        new SlimefunItem(category, EXPLOSIVE_CORE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                new ItemStack(Material.TNT), new ItemStack(Material.GUNPOWDER), new ItemStack(Material.TNT),
                new ItemStack(Material.GUNPOWDER), new ItemStack(Material.NETHER_STAR), new ItemStack(Material.GUNPOWDER),
                new ItemStack(Material.TNT), new ItemStack(Material.GUNPOWDER), new ItemStack(Material.TNT)
        }).register(addon);
    }
}
