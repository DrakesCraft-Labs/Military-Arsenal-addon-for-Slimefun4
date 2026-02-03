package com.Chagui68.weaponsaddon.listeners;

import com.Chagui68.weaponsaddon.items.CustomRecipeItem;
import com.Chagui68.weaponsaddon.items.gui.RecipeViewerGUI;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class SlimefunGuideListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSlimefunGuideClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        Player p = (Player) e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();

        if (clicked == null || !clicked.hasItemMeta()) return;

        // Verificar si es la guía de Slimefun
        String title = e.getView().getTitle();
        if (!title.contains("Slimefun Guide")) return;

        // Obtener el SlimefunItem clickeado
        SlimefunItem sfItem = SlimefunItem.getByItem(clicked);

        if (sfItem instanceof CustomRecipeItem) {
            CustomRecipeItem customItem = (CustomRecipeItem) sfItem;

            e.setCancelled(true);
            p.closeInventory();

            // Abrir GUI de receta después de un tick
            Bukkit.getScheduler().runTaskLater(
                    Bukkit.getPluginManager().getPlugin("WeaponsAddon"),
                    () -> {
                        if (customItem.getGridSize() == CustomRecipeItem.RecipeGridSize.GRID_4x4) {
                            RecipeViewerGUI.open4x4Recipe(p,
                                    ChatColor.stripColor(customItem.getResultItem().getItemMeta().getDisplayName()),
                                    customItem.getResultItem(),
                                    customItem.getFullRecipe());
                        } else {
                            RecipeViewerGUI.open6x6Recipe(p,
                                    ChatColor.stripColor(customItem.getResultItem().getItemMeta().getDisplayName()),
                                    customItem.getResultItem(),
                                    customItem.getFullRecipe());
                        }
                    },
                    1L
            );
        }
    }
}
