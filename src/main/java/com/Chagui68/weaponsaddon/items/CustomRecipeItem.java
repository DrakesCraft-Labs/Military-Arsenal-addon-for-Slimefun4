package com.Chagui68.weaponsaddon.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

public class CustomRecipeItem extends SlimefunItem {

    public enum RecipeGridSize {
        GRID_4x4,
        GRID_6x6
    }

    private final ItemStack[] fullRecipe;
    private final RecipeGridSize gridSize;
    private ItemStack customResult;

    public CustomRecipeItem(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType,
                            ItemStack[] fullRecipe, RecipeGridSize gridSize) {
        super(itemGroup, item, recipeType, new ItemStack[9]);
        this.fullRecipe = fullRecipe;
        this.gridSize = gridSize;
        this.customResult = item;
    }

    public ItemStack[] getFullRecipe() {
        return fullRecipe;
    }

    public RecipeGridSize getGridSize() {
        return gridSize;
    }

    public ItemStack getResultItem() {
        return customResult != null ? customResult : getItem();
    }

    public void setCustomResult(ItemStack result) {
        this.customResult = result;
    }
}
