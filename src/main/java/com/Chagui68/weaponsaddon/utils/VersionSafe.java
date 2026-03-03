package com.Chagui68.weaponsaddon.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;

public class VersionSafe {

    /**
     * Safely gets an Attribute by its name.
     * Handles attributes introduced in 1.20.5+ (SCALE, STEP_HEIGHT, etc.)
     */
    public static Attribute getAttribute(String name) {
        if (name == null)
            return null;
        try {
            // Check if the enum constant exists before calling valueOf to be safe
            for (Attribute attr : Attribute.values()) {
                if (attr.name().equals(name)) {
                    return attr;
                }
            }

            // Legacy fallbacks
            if (name.equals("GENERIC_JUMP_STRENGTH")) {
                for (Attribute attr : Attribute.values()) {
                    if (attr.name().equals("HORSE_JUMP_STRENGTH")) {
                        return attr;
                    }
                }
            }

            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Safely sets the base value of an attribute on an entity.
     * Does nothing if the attribute does not exist or the entity doesn't have it.
     */
    public static void setAttributeBaseValue(LivingEntity entity, String attributeName, double value) {
        Attribute attr = getAttribute(attributeName);
        if (attr != null && entity.getAttribute(attr) != null) {
            entity.getAttribute(attr).setBaseValue(value);
        }
    }

    /**
     * Safely gets an Enchantment by a key that is valid across versions.
     */
    public static Enchantment getEnchantment(String key) {
        // First try by NamespacedKey (Internal name, usually consistent)
        try {
            Enchantment ench = Enchantment.getByKey(NamespacedKey.minecraft(key.toLowerCase()));
            if (ench != null)
                return ench;
        } catch (NoClassDefFoundError | NoSuchMethodError e) {
            // NamespacedKey might be different or getByKey might fail on very old versions
        }

        // Fallback for older versions or specific mappings
        String name = null;
        switch (key.toLowerCase()) {
            case "sharpness":
                name = "DAMAGE_ALL";
                break;
            case "power":
                name = "ARROW_DAMAGE";
                break;
            case "punch":
                name = "ARROW_KNOCKBACK";
                break;
            case "protection":
                name = "PROTECTION_ENVIRONMENTAL";
                break;
            case "projectile_protection":
                name = "PROTECTION_PROJECTILE";
                break;
            case "blast_protection":
                name = "PROTECTION_EXPLOSIONS";
                break;
            case "fire_protection":
                name = "PROTECTION_FIRE";
                break;
            case "respiration":
                name = "OXYGEN";
                break;
            case "looting":
                name = "LOOT_BONUS_MOBS";
                break;
            case "unbreaking":
                name = "DURABILITY";
                break;
            case "efficiency":
                name = "DIG_SPEED";
                break;
            case "smite":
                name = "DAMAGE_UNDEAD";
                break;
            case "bane_of_arthropods":
                name = "DAMAGE_ARTHROPODS";
                break;
        }

        if (name != null) {
            try {
                return Enchantment.getByName(name);
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }

    /**
     * Safely gets a Particle by name.
     * Handles 1.20.5+ renames.
     */
    public static Particle getParticle(String name) {
        try {
            return Particle.valueOf(name);
        } catch (IllegalArgumentException e1) {
            try {
                if (name.equals("DUST"))
                    return Particle.valueOf("REDSTONE");
                if (name.equals("HAPPY_VILLAGER"))
                    return Particle.valueOf("VILLAGER_HAPPY");
                if (name.equals("ANGRY_VILLAGER"))
                    return Particle.valueOf("VILLAGER_ANGRY");
                if (name.equals("EXPLOSION"))
                    return Particle.valueOf("EXPLOSION_NORMAL");
                if (name.equals("LARGE_SMOKE"))
                    return Particle.valueOf("SMOKE_LARGE");
                if (name.equals("EXPLOSION_EMITTER"))
                    return Particle.valueOf("HUGE_EXPLOSION");
                if (name.equals("WITCH"))
                    return Particle.valueOf("SPELL_WITCH");
            } catch (IllegalArgumentException e2) {
                // Ignore
            }
            return null;
        }
    }

    /**
     * Safely gets a Sound by name.
     * Handles 1.20.5+ rename of FIREWORK_ROCKET to FIREWORK.
     */
    public static Sound getSound(String name) {
        try {
            return Sound.valueOf(name);
        } catch (IllegalArgumentException e1) {
            try {
                if (name.contains("FIREWORK_ROCKET")) {
                    return Sound.valueOf(name.replace("FIREWORK_ROCKET", "FIREWORK"));
                }
                if (name.equals("BLOCK_NOTE_BLOCK_HAT"))
                    return Sound.valueOf("BLOCK_NOTE_HAT");
            } catch (IllegalArgumentException e2) {
                // Ignore
            }
            return null;
        }
    }

    /**
     * Safely gets a PotionEffectType by name.
     * Handles 1.20.5+ renames (SLOWNESS -> SLOW, etc.)
     */
    @SuppressWarnings("deprecation")
    public static PotionEffectType getPotionEffectType(String name) {
        try {
            // Try modern name first (1.20.5+)
            PotionEffectType type = PotionEffectType.getByName(name);
            if (type != null) return type;

            // Fallbacks for legacy versions
            if (name.equalsIgnoreCase("SLOWNESS")) {
                return PotionEffectType.getByName("SLOW");
            }
            if (name.equalsIgnoreCase("MINING_FATIGUE")) {
                return PotionEffectType.getByName("SLOW_DIGGING");
            }
            if (name.equalsIgnoreCase("HASTE")) {
                return PotionEffectType.getByName("FAST_DIGGING");
            }
            if (name.equalsIgnoreCase("STRENGTH")) {
                return PotionEffectType.getByName("INCREASE_DAMAGE");
            }
            if (name.equalsIgnoreCase("INSTANT_HEALTH")) {
                return PotionEffectType.getByName("HEAL");
            }
            if (name.equalsIgnoreCase("INSTANT_DAMAGE")) {
                return PotionEffectType.getByName("HARM");
            }
            if (name.equalsIgnoreCase("NAUSEA")) {
                return PotionEffectType.getByName("CONFUSION");
            }
            if (name.equalsIgnoreCase("RESISTANCE")) {
                return PotionEffectType.getByName("DAMAGE_RESISTANCE");
            }
            if (name.equalsIgnoreCase("SPEED")) {
                return PotionEffectType.getByName("SPEED"); // Stable, but safe to check
            }
            if (name.equalsIgnoreCase("FIRE_RESISTANCE")) {
                return PotionEffectType.getByName("FIRE_RESISTANCE");
            }
            if (name.equalsIgnoreCase("JUMP_BOOST")) {
                return PotionEffectType.getByName("JUMP");
            }
            if (name.equalsIgnoreCase("NIGHT_VISION")) {
                return PotionEffectType.getByName("NIGHT_VISION");
            }
            if (name.equalsIgnoreCase("ABSORPTION")) {
                return PotionEffectType.getByName("ABSORPTION");
            }
            if (name.equalsIgnoreCase("SATURATION")) {
                return PotionEffectType.getByName("SATURATION");
            }
            if (name.equalsIgnoreCase("LEVITATION")) {
                return PotionEffectType.getByName("LEVITATION");
            }
            if (name.equalsIgnoreCase("GLOWING")) {
                return PotionEffectType.getByName("GLOWING");
            }
            if (name.equalsIgnoreCase("WITHER")) {
                return PotionEffectType.getByName("WITHER");
            }
            if (name.equalsIgnoreCase("HUNGER")) {
                return PotionEffectType.getByName("HUNGER");
            }
            if (name.equalsIgnoreCase("WEAKNESS")) {
                return PotionEffectType.getByName("WEAKNESS");
            }
            if (name.equalsIgnoreCase("DARKNESS")) {
                return PotionEffectType.getByName("DARKNESS");
            }

            return null;
        } catch (Exception e) {
            return null;
        }
    }
}


