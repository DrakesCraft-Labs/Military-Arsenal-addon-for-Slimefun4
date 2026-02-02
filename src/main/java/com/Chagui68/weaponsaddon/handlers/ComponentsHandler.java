package com.Chagui68.weaponsaddon.handlers;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ComponentsHandler implements Listener {

    // PRIORITY HIGHEST para ejecutar ANTES que Slimefun GPS
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCompassUse(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (item == null || item.getType() != Material.COMPASS) return;

        SlimefunItem sfItem = SlimefunItem.getByItem(item);

        // Si es un item de nuestro addon, cancelar ANTES que Slimefun GPS lo procese
        if (sfItem != null && sfItem.getAddon() != null &&
                sfItem.getAddon().getName().equals("WeaponsAddon")) {
            e.setCancelled(true);
        }
    }
}
