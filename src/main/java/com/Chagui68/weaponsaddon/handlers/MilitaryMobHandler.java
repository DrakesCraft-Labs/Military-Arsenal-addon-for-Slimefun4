package com.Chagui68.weaponsaddon.handlers;

import com.Chagui68.weaponsaddon.items.MachineGun;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.Color;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Random;

public class MilitaryMobHandler implements Listener {

    private final Random random = new Random();

    // Probabilidades (1.0 = 100%)
    private static final double RANGER_CHANCE = 0.5; // 50%

    public MilitaryMobHandler(Plugin plugin) {
    }

    @EventHandler
    public void onSpawn(CreatureSpawnEvent e) {
        // Solo reemplazar spawns naturales y que sean Esqueletos
        // Permitir Spawn Natural, Generación de Mundo y HUEVOS (Vanilla)
        if (e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.NATURAL &&
                e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.CHUNK_GEN &&
                e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.SPAWNER_EGG)
            return;

        if (e.getEntityType() != EntityType.SKELETON)
            return;

        Skeleton skeleton = (Skeleton) e.getEntity();
        double roll = random.nextDouble();

        // Heavy Gunner removido de spawn natural (Es Jefe)
        // Solo Elite Ranger (5% de probabilidad)
        if (roll < RANGER_CHANCE) {
            equipEliteRanger(skeleton);
        }
    }

    public static void equipHeavyGunner(Skeleton boss) {
        // Stats
        boss.setCustomName(ChatColor.DARK_RED + "☠ Heavy Gunner ☠");
        boss.setCustomNameVisible(true);
        boss.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(500.0);
        boss.setHealth(500.0);
        boss.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1.0); // No retroceso
        boss.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3); // Esqueleto es más ágil
        boss.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(18.0); // 9 Corazones de daño melee

        // Tag para identificarlo en la IA
        boss.addScoreboardTag("HeavyGunner");

        // Equipo
        EntityEquipment equip = boss.getEquipment();
        if (equip != null) {
            // Arma: Machine Gun
            equip.setItemInMainHand(MachineGun.MACHINE_GUN.clone());
            equip.setItemInMainHandDropChance(0.001f); // 0.1% probabilidad de drop

            // Secundaria: Cuchillo Táctico (Espada de Hierro)
            ItemStack tacticalKnife = new ItemStack(Material.IRON_SWORD);
            equip.setItemInOffHand(tacticalKnife);
            equip.setItemInOffHandDropChance(0.0f); // No dropear cuchillo

            // Armadura Pesada (Netherite)
            equip.setHelmet(new ItemStack(Material.NETHERITE_HELMET));
            equip.setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE));
            equip.setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS));
            equip.setBoots(new ItemStack(Material.NETHERITE_BOOTS));

            // Efectos visuales de protección
            boss.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 1));
        }
    }

    public static void equipEliteRanger(Skeleton boss) {
        boss.setCustomName(ChatColor.DARK_GREEN + "☠ Elite Ranger ☠");
        boss.setCustomNameVisible(true);

        // Escalar stats por dificultad
        double damage = 30.0;
        double health = 50.0;

        switch (boss.getWorld().getDifficulty()) {
            case EASY:
                damage = 20.0;
                health = 40.0;
                break;
            case NORMAL:
                damage = 30.0;
                health = 50.0;
                break;
            case HARD:
                damage = 45.0; // Muy letal
                health = 80.0;
                break;
            default: // PEACEFUL
                break;
        }

        boss.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
        boss.setHealth(health);
        boss.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.35);
        boss.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(damage);

        // Tag para la IA Híbrida
        boss.addScoreboardTag("EliteRanger");

        EntityEquipment equip = boss.getEquipment();
        if (equip != null) {
            ItemStack bow = new ItemStack(Material.BOW);
            ItemStack sword = new ItemStack(Material.IRON_SWORD);

            // Encantamientos por probabilidad segun dificultad
            applyEnchantments(boss.getWorld().getDifficulty(), bow, sword);

            equip.setItemInMainHand(bow);
            equip.setItemInMainHandDropChance(0.0f);

            equip.setItemInOffHand(sword);
            equip.setItemInOffHandDropChance(0.0f);

            // Armadura de Camuflaje (Cuero Verde - Zombie Camo)
            equip.setHelmet(getColorArmor(Material.LEATHER_HELMET, Color.GREEN));
            equip.setChestplate(getColorArmor(Material.LEATHER_CHESTPLATE, Color.GREEN));
            equip.setLeggings(getColorArmor(Material.LEATHER_LEGGINGS, Color.GREEN));
            equip.setBoots(getColorArmor(Material.LEATHER_BOOTS, Color.GREEN));
        }
    }

    public static void applyEnchantments(org.bukkit.Difficulty difficulty, ItemStack bow, ItemStack sword) {
        if (difficulty == org.bukkit.Difficulty.EASY)
            return;

        Random rnd = new Random();
        boolean isHard = difficulty == org.bukkit.Difficulty.HARD;

        // Probabilidad de encantar: Normal 30%, Hard 70%
        if (rnd.nextDouble() > (isHard ? 0.3 : 0.7)) {
            try {
                // Sword Enchants
                org.bukkit.enchantments.Enchantment sharp = org.bukkit.enchantments.Enchantment.getByName("DAMAGE_ALL");
                org.bukkit.enchantments.Enchantment fire = org.bukkit.enchantments.Enchantment.getByName("FIRE_ASPECT");

                if (sharp != null)
                    sword.addEnchantment(sharp, isHard ? 3 : 1);
                if (isHard && rnd.nextBoolean() && fire != null)
                    sword.addEnchantment(fire, 1);

                // Bow Enchants
                org.bukkit.enchantments.Enchantment power = org.bukkit.enchantments.Enchantment
                        .getByName("ARROW_DAMAGE");
                org.bukkit.enchantments.Enchantment punch = org.bukkit.enchantments.Enchantment
                        .getByName("ARROW_KNOCKBACK");

                if (power != null)
                    bow.addEnchantment(power, isHard ? 3 : 1);
                if (isHard && rnd.nextBoolean() && punch != null)
                    bow.addEnchantment(punch, 1);
            } catch (Exception e) {
                // Ignorar errores de encantamiento para no romper el spawn
            }
        }
    }

    private static ItemStack getColorArmor(Material mat, Color color) {
        ItemStack item = new ItemStack(mat);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        if (meta != null) {
            meta.setColor(color);
            item.setItemMeta(meta);
        }
        return item;
    }
}
