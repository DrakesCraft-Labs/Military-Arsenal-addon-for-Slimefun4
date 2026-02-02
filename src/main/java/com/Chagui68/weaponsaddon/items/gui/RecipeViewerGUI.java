package com.Chagui68.weaponsaddon.items.gui;

import com.Chagui68.weaponsaddon.items.CustomRecipeItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RecipeViewerGUI implements Listener {

    private static final Map<UUID, String> openViewers = new HashMap<>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack item = e.getItem();

        if (item == null || !p.isSneaking()) return;

        SlimefunItem sfItem = SlimefunItem.getByItem(item);

        if (sfItem instanceof CustomRecipeItem) {
            e.setCancelled(true);
            CustomRecipeItem customItem = (CustomRecipeItem) sfItem;

            if (customItem.getGridSize() == CustomRecipeItem.RecipeGridSize.GRID_4x4) {
                open4x4Recipe(p, ChatColor.stripColor(customItem.getResultItem().getItemMeta().getDisplayName()),
                        customItem.getResultItem(), customItem.getFullRecipe());
            } else {
                open6x6Recipe(p, ChatColor.stripColor(customItem.getResultItem().getItemMeta().getDisplayName()),
                        customItem.getResultItem(), customItem.getFullRecipe());
            }
        }
    }

    public static void open4x4Recipe(Player p, String itemName, ItemStack result, ItemStack[] recipe) {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_RED + "⚒ Recipe: " + itemName);

        ItemStack background = new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " ");
        ItemStack border = new CustomItemStack(Material.ORANGE_STAINED_GLASS_PANE, ChatColor.GOLD + "▓");

        for (int i = 0; i < 54; i++) {
            inv.setItem(i, background);
        }

        int[] gridSlots = {
                11, 12, 13, 14,
                20, 21, 22, 23,
                29, 30, 31, 32,
                38, 39, 40, 41
        };

        for (int i = 0; i < Math.min(16, recipe.length); i++) {
            if (recipe[i] != null) {
                inv.setItem(gridSlots[i], recipe[i]);
            }
        }

        int[] borderSlots = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 18, 27, 36, 45, 46, 47, 48, 49, 50, 51, 52, 53, 17, 26, 35, 44};
        for (int slot : borderSlots) {
            inv.setItem(slot, border);
        }

        inv.setItem(25, new CustomItemStack(Material.LIME_STAINED_GLASS_PANE, ChatColor.GREEN + "⬇ RESULT ⬇"));
        inv.setItem(34, result);

        inv.setItem(53, new CustomItemStack(
                Material.SMITHING_TABLE,
                ChatColor.GOLD + "ℹ Military Crafting Table",
                "",
                ChatColor.YELLOW + "4×4 Advanced Crafting",
                ChatColor.GRAY + "Place items exactly as shown",
                "",
                ChatColor.DARK_GRAY + "Close inventory to return"
        ));

        inv.setItem(49, new CustomItemStack(
                Material.BARRIER,
                ChatColor.RED + "✖ Close",
                "",
                ChatColor.GRAY + "Click to close"
        ));

        p.openInventory(inv);
        openViewers.put(p.getUniqueId(), itemName);
    }

    public static void open6x6Recipe(Player p, String itemName, ItemStack result, ItemStack[] recipe) {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_RED + "⚙ Recipe: " + itemName);

        ItemStack background = new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " ");
        ItemStack border = new CustomItemStack(Material.RED_STAINED_GLASS_PANE, ChatColor.DARK_RED + "▓");

        for (int i = 0; i < 54; i++) {
            inv.setItem(i, background);
        }

        int[] gridSlots = {
                1, 2, 3, 4, 5, 6,
                10, 11, 12, 13, 14, 15,
                19, 20, 21, 22, 23, 24,
                28, 29, 30, 31, 32, 33,
                37, 38, 39, 40, 41, 42,
                46, 47, 48, 49, 50, 51
        };

        for (int i = 0; i < Math.min(36, recipe.length); i++) {
            if (recipe[i] != null) {
                inv.setItem(gridSlots[i], recipe[i]);
            }
        }

        int[] borderSlots = {0, 7, 8, 9, 16, 17, 18, 25, 26, 27, 34, 35, 36, 43, 44, 45, 52, 53};
        for (int slot : borderSlots) {
            inv.setItem(slot, border);
        }

        inv.setItem(8, new CustomItemStack(Material.LIME_STAINED_GLASS_PANE, ChatColor.GREEN + "⬇ RESULT ⬇"));
        inv.setItem(17, result);

        inv.setItem(0, new CustomItemStack(
                Material.RESPAWN_ANCHOR,
                ChatColor.DARK_RED + "ℹ Machine Fabricator",
                "",
                ChatColor.RED + "6×6 Ultimate Crafting",
                ChatColor.GRAY + "Place items exactly as shown",
                "",
                ChatColor.DARK_GRAY + "Close inventory to return"
        ));

        inv.setItem(53, new CustomItemStack(
                Material.BARRIER,
                ChatColor.RED + "✖ Close",
                "",
                ChatColor.GRAY + "Click to close"
        ));

        p.openInventory(inv);
        openViewers.put(p.getUniqueId(), itemName);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        String title = e.getView().getTitle();
        if (!title.startsWith(ChatColor.DARK_RED + "⚒ Recipe:") &&
                !title.startsWith(ChatColor.DARK_RED + "⚙ Recipe:")) return;

        Player p = (Player) e.getWhoClicked();

        // CANCELAR TODOS LOS CLICKS
        e.setCancelled(true);

        int slot = e.getRawSlot();

        // Solo permitir cerrar con el botón BARRIER
        if ((slot == 49 || slot == 53) && e.getCurrentItem() != null &&
                e.getCurrentItem().getType() == Material.BARRIER) {
            p.closeInventory();
            openViewers.remove(p.getUniqueId());
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        String title = e.getView().getTitle();
        if (title.startsWith(ChatColor.DARK_RED + "⚒ Recipe:") ||
                title.startsWith(ChatColor.DARK_RED + "⚙ Recipe:")) {
            e.setCancelled(true);
        }
    }
}
