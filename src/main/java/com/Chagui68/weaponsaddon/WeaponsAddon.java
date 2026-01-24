package com.Chagui68.weaponsaddon;

import com.Chagui68.weaponsaddon.handlers.MachineGunHandler;
import com.Chagui68.weaponsaddon.items.BombardmentTerminal;
import com.Chagui68.weaponsaddon.items.MachineGun;
import com.Chagui68.weaponsaddon.items.MachineGunAmmo;
import com.Chagui68.weaponsaddon.items.MilitaryComponents;
import com.Chagui68.weaponsaddon.items.machines.TerminalClickHandler;
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


        NamespacedKey mainKey = new NamespacedKey(this, "military_arsenal");
        CustomItemStack mainItem = new CustomItemStack(
                Material.NETHERITE_SWORD,
                "&4⚔ &c&lMILITARY ARSENAL",
                "",
                "&7Advanced military equipment",
                "&7and tactical systems",
                "",
                "&e▶ Click to open categories",
                "&8⇨ Main Category"
        );
        NestedItemGroup mainGroup = new NestedItemGroup(mainKey, mainItem, 2);


        NamespacedKey componentsKey = new NamespacedKey(this, "military_components");
        CustomItemStack componentsItem = new CustomItemStack(
                Material.REDSTONE_BLOCK,
                "&6⚙ &eMilitary Components",
                "",
                "&7Basic materials for crafting",
                "&7military equipment",
                "",
                "&8⇨ Level 1 Components"
        );
        SubItemGroup componentsGroup = new SubItemGroup(componentsKey, mainGroup, componentsItem);


        NamespacedKey weaponsKey = new NamespacedKey(this, "military_weapons");
        CustomItemStack weaponsItem = new CustomItemStack(
                Material.DIAMOND_SWORD,
                "&c⚔ &4Military Weapons",
                "",
                "&7Advanced combat equipment",
                "&7and ammunition",
                "",
                "&8⇨ Tier 2 Weapons"
        );
        SubItemGroup weaponsGroup = new SubItemGroup(weaponsKey, mainGroup, weaponsItem);


        NamespacedKey machinesKey = new NamespacedKey(this, "military_machines");
        CustomItemStack machinesItem = new CustomItemStack(
                Material.OBSERVER,
                "&4💣 &cMilitary Machines",
                "",
                "&7Automated warfare systems",
                "&7and tactical devices",
                "",
                "&8⇨ Tier 2 Machines"
        );
        SubItemGroup machinesGroup = new SubItemGroup(machinesKey, mainGroup, machinesItem);


        mainGroup.register(this);


        getLogger().info("Registering Military Components...");
        MilitaryComponents.register(this, componentsGroup);


        getLogger().info("Registering Military Weapons...");
        MachineGunAmmo.register(this, weaponsGroup);
        MachineGun.register(this, weaponsGroup);


        getLogger().info("Registering Military Machines...");
        BombardmentTerminal.register(this, machinesGroup);


        getServer().getPluginManager().registerEvents(new MachineGunHandler(), this);

        TerminalClickHandler.setPlugin(this);
        getServer().getPluginManager().registerEvents(new TerminalClickHandler(), this);

        getLogger().info("========================================");
        getLogger().info("WeaponsAddon enabled successfully!");
        getLogger().info("Main Category: 1 | Subcategories: 3");
        getLogger().info("Total Items: 9");
        getLogger().info("========================================");
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
        return "https://github.com/Chagui68/WeaponsAddon/issues";
    }
}
