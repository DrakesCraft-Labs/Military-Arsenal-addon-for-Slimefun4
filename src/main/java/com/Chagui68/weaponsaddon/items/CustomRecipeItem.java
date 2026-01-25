package com.Chagui68.weaponsaddon.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CustomRecipeItem extends SlimefunItem {

    public enum RecipeGridSize {
        GRID_4x4(4),
        GRID_6x6(6);

        private final int size;

        RecipeGridSize(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }
    }

    private final ItemStack[] fullRecipe;
    private final RecipeGridSize gridSize;
    private ItemStack customResult = null;

    public CustomRecipeItem(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, RecipeGridSize gridSize) {
        super(itemGroup, item, recipeType, new ItemStack[9]);
        this.fullRecipe = recipe;
        this.gridSize = gridSize;
    }

    public ItemStack[] getFullRecipe() {
        return fullRecipe;
    }

    public RecipeGridSize getGridSize() {
        return gridSize;
    }

    public void setCustomResult(ItemStack result) {
        this.customResult = result;
    }

    public ItemStack getResultItem() {
        return customResult != null ? customResult : getItem();
    }

    public void showRecipe(Player p) {
        if (gridSize == RecipeGridSize.GRID_4x4) {
            show4x4Recipe(p);
        } else if (gridSize == RecipeGridSize.GRID_6x6) {
            show6x6Recipe(p);
        }
    }

    private void show4x4Recipe(Player p) {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Recipe: " + ChatColor.stripColor(getResultItem().getItemMeta().getDisplayName()));

        ItemStack background = new CustomItemStack(Material.GRAY_STAINED_GLASS_PANE, " ");
        for (int i = 0; i < 54; i++) {
            inv.setItem(i, background);
        }

        ItemStack border = new CustomItemStack(Material.ORANGE_STAINED_GLASS_PANE, ChatColor.GOLD + "▓");
        int[] borderSlots = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 18, 27, 36, 45, 46, 47, 48, 49, 50, 51, 52, 53, 17, 26, 35, 44};
        for (int slot : borderSlots) {
            inv.setItem(slot, border);
        }

        int[] gridSlots = {11, 12, 13, 14, 20, 21, 22, 23, 29, 30, 31, 32, 38, 39, 40, 41};

        for (int i = 0; i < 16 && i < fullRecipe.length; i++) {
            ItemStack item = fullRecipe[i];
            if (item != null) {
                inv.setItem(gridSlots[i], item.clone());
            }
        }

        inv.setItem(25, new CustomItemStack(Material.LIME_STAINED_GLASS_PANE,
                ChatColor.GREEN + "⬇ RESULT ⬇"));

        inv.setItem(34, getResultItem().clone());

        inv.setItem(53, new CustomItemStack(Material.SMITHING_TABLE,
                ChatColor.GOLD + "ℹ Military Crafting Table",
                "",
                ChatColor.YELLOW + "4×4 Advanced Crafting",
                ChatColor.GRAY + "Place items exactly as shown",
                "",
                ChatColor.DARK_GRAY + "Close inventory to return"));

        p.openInventory(inv);
    }

    private void show6x6Recipe(Player p) {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GOLD  + ChatColor.stripColor(getResultItem().getItemMeta().getDisplayName()));

        ItemStack background = new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " ");
        for (int i = 0; i < 54; i++) {
            inv.setItem(i, background);
        }

        ItemStack border = new CustomItemStack(Material.RED_STAINED_GLASS_PANE, ChatColor.DARK_RED + "▓");
        int[] borderSlots = {0, 7, 8, 9, 16, 17, 18, 25, 26, 27, 34, 35, 36, 43, 44, 45, 52, 53};
        for (int slot : borderSlots) {
            inv.setItem(slot, border);
        }

        int[] gridSlots = {1, 2, 3, 4, 5, 6, 10, 11, 12, 13, 14, 15, 19, 20, 21, 22, 23, 24, 28, 29, 30, 31, 32, 33, 37, 38, 39, 40, 41, 42, 46, 47, 48, 49, 50, 51};

        for (int i = 0; i < 36 && i < fullRecipe.length; i++) {
            ItemStack item = fullRecipe[i];
            if (item != null) {
                inv.setItem(gridSlots[i], item.clone());
            }
        }

        inv.setItem(8, new CustomItemStack(Material.LIME_STAINED_GLASS_PANE,
                ChatColor.GREEN + "⬇ RESULT ⬇"));

        inv.setItem(17, getResultItem().clone());

        inv.setItem(0, new CustomItemStack(Material.RESPAWN_ANCHOR,
                ChatColor.DARK_RED + "ℹ Machine Fabricator",
                "",
                ChatColor.RED + "6×6 Ultimate Crafting",
                ChatColor.GRAY + "Place items exactly as shown",
                "",
                ChatColor.DARK_GRAY + "Close inventory to return"));

        p.openInventory(inv);
    }
}
