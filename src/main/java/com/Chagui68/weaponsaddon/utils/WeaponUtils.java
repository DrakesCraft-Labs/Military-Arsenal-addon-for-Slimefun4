package com.Chagui68.weaponsaddon.utils;

import com.Chagui68.weaponsaddon.WeaponsAddon;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collection;

public class WeaponUtils {

    /**
     * Calculates the total damage of an item, including base damage,
     * attribute modifiers (upgrades), and enchantments.
     * 
     * @param item       The item to calculate damage for
     * @param baseDamage The base damage of the custom weapon (if applicable)
     * @param target     The target being hit (for Smite/Bane of Arthropods
     *                   calculation)
     * @return The total damage value
     */
    public static double calculateDamage(ItemStack item, double baseDamage, LivingEntity target) {
        if (item == null || item.getType() == Material.AIR)
            return 1.0;

        double damage = baseDamage;
        ItemMeta meta = item.getItemMeta();

        // 1. Attribute Modifiers (Upgrades from Upgrade Table)
        if (meta != null && meta.hasAttributeModifiers()) {
            Collection<AttributeModifier> mods = meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE);
            if (mods != null) {
                for (AttributeModifier mod : mods) {
                    if (mod.getOperation() == AttributeModifier.Operation.ADD_NUMBER) {
                        damage += mod.getAmount();
                    }
                }
            }
        }

        // 2. Enchantments
        // Sharpness
        if (item.containsEnchantment(Enchantment.SHARPNESS)) {
            int level = item.getEnchantmentLevel(Enchantment.SHARPNESS);
            if (level > 0) {
                damage += 0.5 * level + 0.5;
            }
        }

        // Power (for ranged weapons)
        if (item.containsEnchantment(Enchantment.POWER)) {
            int level = item.getEnchantmentLevel(Enchantment.POWER);
            if (level > 0) {
                // Vanilla formula approximation for lore consistency
                damage += 0.5 * (level + 1);
            }
        }

        // Smite / Bane of Arthropods (Conditional)
        if (target != null) {
            if (item.containsEnchantment(Enchantment.SMITE) && isUndead(target.getType())) {
                damage += 2.5 * item.getEnchantmentLevel(Enchantment.SMITE);
            }
            if (item.containsEnchantment(Enchantment.BANE_OF_ARTHROPODS) && isArthropod(target.getType())) {
                damage += 2.5 * item.getEnchantmentLevel(Enchantment.BANE_OF_ARTHROPODS);
            }
        }

        return damage;
    }

    /**
     * Calculates the adjusted fire interval based on speed upgrades.
     * 
     * @param item              The weapon item
     * @param baseIntervalTicks The standard cooldown/burst interval
     * @return The new interval in ticks
     */
    public static long calculateFireInterval(ItemStack item, long baseIntervalTicks) {
        if (item == null || !item.hasItemMeta())
            return baseIntervalTicks;

        ItemMeta meta = item.getItemMeta();
        NamespacedKey key = new NamespacedKey(WeaponsAddon.getInstance(), "upgrade_speed_level");
        int speedLevel = meta.getPersistentDataContainer().getOrDefault(key, PersistentDataType.INTEGER, 0);

        if (speedLevel <= 0)
            return baseIntervalTicks;

        // Example: Each level reduces interval by 10% (stacking)
        double reduction = 1.0 - (speedLevel * 0.10);
        return Math.max(1, Math.round(baseIntervalTicks * reduction));
    }

    private static boolean isUndead(EntityType type) {
        switch (type) {
            case ZOMBIE:
            case ZOMBIE_VILLAGER:
            case HUSK:
            case DROWNED:
            case SKELETON:
            case STRAY:
            case WITHER_SKELETON:
            case WITHER:
            case SKELETON_HORSE:
            case ZOMBIE_HORSE:
            case PHANTOM:
            case ZOGLIN:
                return true;
            default:
                return false;
        }
    }

    private static boolean isArthropod(EntityType type) {
        switch (type) {
            case SPIDER:
            case CAVE_SPIDER:
            case BEE:
            case SILVERFISH:
            case ENDERMITE:
                return true;
            default:
                return false;
        }
    }
}
