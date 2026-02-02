package com.Chagui68.weaponsaddon;

import com.Chagui68.weaponsaddon.handlers.MachineGunHandler;
import com.Chagui68.weaponsaddon.items.MachineGun;
import com.Chagui68.weaponsaddon.items.MachineGunAmmo;
import com.Chagui68.weaponsaddon.items.AntimatterRifle;
import com.Chagui68.weaponsaddon.items.components.MilitaryComponents;
import com.Chagui68.weaponsaddon.items.machines.*;
import com.Chagui68.weaponsaddon.items.gui.RecipeViewerGUI;
import com.Chagui68.weaponsaddon.listeners.SlimefunGuideListener;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public class WeaponsAddon extends JavaPlugin implements SlimefunAddon {

    private static WeaponsAddon instance;

    @Override
    public void onEnable() {
        instance = this;
        Config config = new Config(this);

        // Crear categorías
        NestedItemGroup mainGroup = createMainGroup();
        SubItemGroup componentsGroup = createComponentsGroup(mainGroup);
        SubItemGroup weaponsGroup = createWeaponsGroup(mainGroup);
        SubItemGroup machinesGroup = createMachinesGroup(mainGroup);

        mainGroup.register(this);

        // Registrar handlers
        registerEventHandlers();

        // Registrar items
        registerCraftingStations(weaponsGroup, machinesGroup);
        MilitaryComponents.register(this, componentsGroup);
        MachineGunAmmo.register(this, weaponsGroup);
        MachineGun.register(this, weaponsGroup);
        AntimatterRifle.register(this, weaponsGroup);
        BombardmentTerminal.register(this, machinesGroup);
        BombardmentTerminalRecipeDisplay.register(this, machinesGroup);
        AntimatterPedestal.register(this, machinesGroup);
        AntimatterRitual.register(this, machinesGroup);

        getLogger().info("✓ WeaponsAddon enabled successfully!");
    }

    private NestedItemGroup createMainGroup() {
        NamespacedKey mainKey = new NamespacedKey(this, "military_arsenal");
        CustomItemStack mainItem = new CustomItemStack(
                Material.NETHERITE_SWORD,
                "&4⚔ &c&lMILITARY ARSENAL",
                "",
                "&7Advanced military equipment",
                "&7and tactical systems",
                "",
                "&e▶ Click to open categories"
        );
        return new NestedItemGroup(mainKey, mainItem, 2);
    }

    private SubItemGroup createComponentsGroup(NestedItemGroup parent) {
        NamespacedKey key = new NamespacedKey(this, "military_components");
        CustomItemStack item = new CustomItemStack(
                Material.REDSTONE_BLOCK,
                "&6⚙ &eMilitary Components",
                "",
                "&7Basic materials for crafting",
                "&7military equipment"
        );
        return new SubItemGroup(key, parent, item);
    }

    private SubItemGroup createWeaponsGroup(NestedItemGroup parent) {
        NamespacedKey key = new NamespacedKey(this, "military_weapons");
        CustomItemStack item = new CustomItemStack(
                Material.DIAMOND_SWORD,
                "&c⚔ &4Military Weapons",
                "",
                "&7Advanced combat equipment",
                "&7and ammunition"
        );
        return new SubItemGroup(key, parent, item);
    }

    private SubItemGroup createMachinesGroup(NestedItemGroup parent) {
        NamespacedKey key = new NamespacedKey(this, "military_machines");
        CustomItemStack item = new CustomItemStack(
                Material.OBSERVER,
                "&4💣 &cMilitary Machines",
                "",
                "&7Automated warfare systems",
                "&7and tactical devices"
        );
        return new SubItemGroup(key, parent, item);
    }

    private void registerEventHandlers() {
        getServer().getPluginManager().registerEvents(new AmmunitionWorkshopHandler(), this);
        getServer().getPluginManager().registerEvents(new MilitaryCraftingHandler(), this);
        getServer().getPluginManager().registerEvents(new MachineFabricatorHandler(), this);
        getServer().getPluginManager().registerEvents(new MachineGunHandler(), this);
        getServer().getPluginManager().registerEvents(new RecipeViewerGUI(), this);
        getServer().getPluginManager().registerEvents(new SlimefunGuideListener(), this);
        TerminalClickHandler.setPlugin(this);
        getServer().getPluginManager().registerEvents(new TerminalClickHandler(), this);
    }

    private void registerCraftingStations(SubItemGroup weaponsGroup, SubItemGroup machinesGroup) {
        AmmunitionWorkshop.register(this, weaponsGroup);
        MilitaryCraftingTable.register(this, machinesGroup);
        MilitaryMachineFabricator.register(this, machinesGroup);
    }

    @Override
    public void onDisable() {
        getLogger().info("WeaponsAddon disabled!");
    }

    public static WeaponsAddon getInstance() {
        return instance;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/Chagui68/Military-Arsenal-addon-for-Slimefun4/issues";
    }
}
