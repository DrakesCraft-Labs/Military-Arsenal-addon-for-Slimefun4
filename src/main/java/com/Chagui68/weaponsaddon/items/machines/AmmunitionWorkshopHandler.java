package com.Chagui68.weaponsaddon.items.machines;

import com.Chagui68.weaponsaddon.items.MachineGunAmmo;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AmmunitionWorkshopHandler implements Listener {

    private static final Map<UUID, Location> openWorkshops = new HashMap<>();

    @EventHandler
    public void onWorkshopClick(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Block block = e.getClickedBlock();
        if (block == null) return;

        SlimefunItem sfItem = BlockStorage.check(block);
        if (sfItem == null || !sfItem.getId().equals("AMMUNITION_WORKSHOP")) return;

        e.setCancelled(true);
        Player p = e.getPlayer();
        Location blockLoc = block.getLocation();

        openWorkshopGUI(p, blockLoc);
    }

    private void openWorkshopGUI(Player p, Location blockLoc) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GOLD + "Ammunition Workshop");

        // Background
        for (int i = 9; i < 27; i++) {
            inv.setItem(i, new CustomItemStack(Material.GRAY_STAINED_GLASS_PANE, " "));
        }

        // Load saved items (3x3 grid = slots 0-8)
        for (int i = 0; i < 9; i++) {
            String itemData = BlockStorage.getLocationInfo(blockLoc, "slot_" + i);
            if (itemData != null && !itemData.isEmpty()) {
                ItemStack item = deserializeItemStack(itemData);
                if (item != null) {
                    inv.setItem(i, item);
                }
            }
        }

        // Output arrow
        inv.setItem(13, new CustomItemStack(Material.LIME_STAINED_GLASS_PANE,
                ChatColor.GREEN + "⬇ OUTPUT ⬇"));

        // Output slot
        inv.setItem(22, null);

        // Craft button
        inv.setItem(25, new CustomItemStack(Material.CRAFTING_TABLE,
                ChatColor.GOLD + "▶ CRAFT ◀",
                "",
                ChatColor.GRAY + "Click to craft item",
                ChatColor.YELLOW + "3×3 Recipe Grid"));

        p.openInventory(inv);
        openWorkshops.put(p.getUniqueId(), blockLoc);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (!(e.getPlayer() instanceof Player)) return;
        Player p = (Player) e.getPlayer();

        if (!e.getView().getTitle().equals(ChatColor.GOLD + "Ammunition Workshop")) return;

        Location blockLoc = openWorkshops.remove(p.getUniqueId());
        if (blockLoc == null) return;

        // Save all items to BlockStorage
        Inventory inv = e.getInventory();
        for (int i = 0; i < 9; i++) {
            ItemStack item = inv.getItem(i);
            if (item != null && item.getType() != Material.AIR) {
                BlockStorage.addBlockInfo(blockLoc, "slot_" + i, serializeItemStack(item));
            } else {
                BlockStorage.addBlockInfo(blockLoc, "slot_" + i, "");
            }
        }

        p.sendMessage(ChatColor.GREEN + "✓ Workshop inventory saved!");
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        if (!e.getView().getTitle().equals(ChatColor.GOLD + "Ammunition Workshop")) return;

        Player p = (Player) e.getWhoClicked();
        int slot = e.getRawSlot();

        // Block clicking UI slots
        if (slot >= 9 && slot < 27 && slot != 22) {
            e.setCancelled(true);
        }

        // Craft button clicked
        if (slot == 25 && e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.CRAFTING_TABLE) {
            e.setCancelled(true);

            Location blockLoc = openWorkshops.get(p.getUniqueId());
            if (blockLoc != null) {
                attemptCraft(p, e.getInventory());
            }
        }
    }

    private void attemptCraft(Player p, Inventory inv) {
        // Get 3x3 grid items
        ItemStack[] grid = new ItemStack[9];
        for (int i = 0; i < 9; i++) {
            grid[i] = inv.getItem(i);
        }

        // Check for Machine Gun Ammo recipe
        if (matchesAmmoRecipe(grid)) {
            // Clear grid
            for (int i = 0; i < 9; i++) {
                ItemStack item = grid[i];
                if (item != null && item.getType() != Material.AIR) {
                    item.setAmount(item.getAmount() - 1);
                    if (item.getAmount() <= 0) {
                        inv.setItem(i, null);
                    }
                }
            }

            // Give output
            ItemStack output = MachineGunAmmo.MACHINE_GUN_AMMO.clone();
            output.setAmount(8);
            inv.setItem(22, output);

            p.sendMessage(ChatColor.GREEN + "✓ Crafted 8x Machine Gun Bullets!");
        } else {
            p.sendMessage(ChatColor.RED + "✗ Invalid recipe!");
        }
    }

    private boolean matchesAmmoRecipe(ItemStack[] grid) {
        // Recipe pattern:
        // [empty] [copper] [empty]
        // [iron]  [gunpow] [iron]
        // [empty] [nugget] [empty]

        return isEmpty(grid[0]) &&
                isType(grid[1], Material.COPPER_INGOT) &&
                isEmpty(grid[2]) &&
                isType(grid[3], Material.IRON_INGOT) &&
                isType(grid[4], Material.GUNPOWDER) &&
                isType(grid[5], Material.IRON_INGOT) &&
                isEmpty(grid[6]) &&
                isType(grid[7], Material.IRON_NUGGET) &&
                isEmpty(grid[8]);
    }

    private boolean isEmpty(ItemStack item) {
        return item == null || item.getType() == Material.AIR;
    }

    private boolean isType(ItemStack item, Material type) {
        return item != null && item.getType() == type;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        SlimefunItem sfItem = BlockStorage.check(block);

        if (sfItem != null && sfItem.getId().equals("AMMUNITION_WORKSHOP")) {
            Location blockLoc = block.getLocation();

            // Drop all saved items
            for (int i = 0; i < 9; i++) {
                String itemData = BlockStorage.getLocationInfo(blockLoc, "slot_" + i);
                if (itemData != null && !itemData.isEmpty()) {
                    ItemStack item = deserializeItemStack(itemData);
                    if (item != null) {
                        block.getWorld().dropItemNaturally(blockLoc, item);
                    }
                }
            }
        }
    }

    private String serializeItemStack(ItemStack item) {
        SlimefunItem sfItem = SlimefunItem.getByItem(item);
        if (sfItem != null) {
            return "SF:" + sfItem.getId() + ":" + item.getAmount();
        }
        return "VANILLA:" + item.getType() + ":" + item.getAmount();
    }

    private ItemStack deserializeItemStack(String data) {
        try {
            String[] parts = data.split(":");

            if (parts[0].equals("SF")) {
                // Slimefun item
                SlimefunItem sfItem = SlimefunItem.getById(parts[1]);
                if (sfItem != null) {
                    ItemStack item = sfItem.getItem().clone();
                    item.setAmount(Integer.parseInt(parts[2]));
                    return item;
                }
            } else if (parts[0].equals("VANILLA")) {
                // Vanilla item
                Material material = Material.valueOf(parts[1]);
                int amount = Integer.parseInt(parts[2]);
                return new ItemStack(material, amount);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
