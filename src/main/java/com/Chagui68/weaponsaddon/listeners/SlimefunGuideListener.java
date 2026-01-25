package com.Chagui68.weaponsaddon.listeners;

import com.Chagui68.weaponsaddon.items.CustomRecipeItem;
import com.Chagui68.weaponsaddon.items.gui.RecipeViewerGUI;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class SlimefunGuideListener implements Listener {

    @EventHandler
    public void onGuideClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        Player p = (Player) e.getWhoClicked();
        String title = e.getView().getTitle();

        if (!title.contains("Slimefun Guide")) return;

        ItemStack clicked = e.getCurrentItem();
        if (clicked == null || clicked.getType() == Material.AIR) return;

        SlimefunItem sfItem = SlimefunItem.getByItem(clicked);
        if (!(sfItem instanceof CustomRecipeItem)) return;

        CustomRecipeItem customItem = (CustomRecipeItem) sfItem;

        if (e.isRightClick() && e.isShiftClick()) {
            e.setCancelled(true);

            String itemName = ChatColor.stripColor(clicked.getItemMeta().getDisplayName());
            ItemStack result = customItem.getItem();
            ItemStack[] fullRecipe = customItem.getFullRecipe();

            if (customItem.getGridSize() == CustomRecipeItem.RecipeGridSize.GRID_4x4) {
                RecipeViewerGUI.open4x4Recipe(p, itemName, result, fullRecipe);
            } else if (customItem.getGridSize() == CustomRecipeItem.RecipeGridSize.GRID_6x6) {
                RecipeViewerGUI.open6x6Recipe(p, itemName, result, fullRecipe);
            }

            p.sendMessage(ChatColor.GREEN + "✓ Opening complete recipe viewer...");
        }
    }
}
