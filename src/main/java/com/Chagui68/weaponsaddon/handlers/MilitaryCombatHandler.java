package com.Chagui68.weaponsaddon.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class MilitaryCombatHandler implements Listener {

    private final Plugin plugin;

    public MilitaryCombatHandler(Plugin plugin) {
        this.plugin = plugin;
        startCombatTask();
    }

    // --- DAMAGE HANDLER ---
    @EventHandler
    public void onCombatDamage(EntityDamageByEntityEvent e) {
        // Daño de Arco para Elite Ranger
        if (e.getDamager() instanceof Arrow) {
            Arrow arrow = (Arrow) e.getDamager();
            if (arrow.getShooter() instanceof Skeleton) {
                Skeleton shooter = (Skeleton) arrow.getShooter();
                if (shooter.getScoreboardTags().contains("EliteRanger")) {
                    // DAÑO DE RANGO Configurable
                    double damage = 24.0; // Normal

                    switch (shooter.getWorld().getDifficulty()) {
                        case EASY:
                            damage = 12.0;
                            break;
                        case NORMAL:
                            damage = 24.0;
                            break;
                        case HARD:
                            damage = 32.0;
                            break;
                        default:
                            break;
                    }

                    e.setDamage(damage);
                }
            }
        }
    }

    // --- TARGET HANDLER ---
    @EventHandler
    public void onEntityTarget(org.bukkit.event.entity.EntityTargetLivingEntityEvent e) {
        if (e.getEntity() instanceof Skeleton) {
            Skeleton skeleton = (Skeleton) e.getEntity();
            if (skeleton.getScoreboardTags().contains("EliteRanger")) {
                // Solo permitir atacar a Jugadores
                if (!(e.getTarget() instanceof Player)) {
                    e.setCancelled(true);
                }
            }
        }
    }

    // --- AI TASK START ---
    private void startCombatTask() {
        // Tarea repetitiva cada 20 ticks (1 segundo) - Chequeo rápido para cambios de
        // arma
        new BukkitRunnable() {
            @Override
            public void run() {
                scanHybridMobs();
            }
        }.runTaskTimer(plugin, 20L, 20L);
    }

    private void scanHybridMobs() {
        for (World world : Bukkit.getWorlds()) {
            for (Skeleton skeleton : world.getEntitiesByClass(Skeleton.class)) {
                // Verificar si es nuestro Elite Ranger
                if (skeleton.getScoreboardTags().contains("EliteRanger") && !skeleton.isDead()) {
                    handleHybridAI(skeleton);
                }
            }
        }
    }

    // --- AI LOGIC ---
    private void handleHybridAI(Skeleton ranger) {
        LivingEntity target = ranger.getTarget();
        if (target == null || target.isDead() || target.getWorld() != ranger.getWorld())
            return;

        double distance = ranger.getLocation().distance(target.getLocation());
        EntityEquipment equip = ranger.getEquipment();
        if (equip == null)
            return;

        ItemStack mainHand = equip.getItemInMainHand();
        ItemStack offHand = equip.getItemInOffHand();

        // Lógica Híbrida: Cambio de Arma según distancia
        if (distance <= 5.0) {
            // Cerca: Usar Espada (Melee AI)
            if (mainHand.getType() != Material.IRON_SWORD) {
                // 1. Buscar la Espada (Debería estar en la offhand o es nueva)
                ItemStack swordToEquip = offHand;

                if (swordToEquip == null || swordToEquip.getType() != Material.IRON_SWORD) {
                    swordToEquip = new ItemStack(Material.IRON_SWORD);
                    // Intentar recuperar encantamientos si es nueva (no debería pasar si spawneó
                    // bien)
                    MilitaryMobHandler.applyEnchantments(ranger.getWorld().getDifficulty(), new ItemStack(Material.BOW),
                            swordToEquip);
                }

                // 2. Equipar Espada en Principal
                equip.setItemInMainHand(swordToEquip);

                // 3. BORRAR la Secundadía (Para evitar que dispare flechas a quemarropa use el
                // arco en la offhand)
                // Sacrificamos el Arco actual para asegurar el comportamiento Melee
                equip.setItemInOffHand(null);

                ranger.getWorld().playSound(ranger.getLocation(), Sound.ITEM_ARMOR_EQUIP_IRON, 1.0f, 2.0f);
            }
        } else {
            // Lejos: Usar Arco (Ranged AI)
            if (mainHand.getType() != Material.BOW) {
                // 1. Guardar la Espada actual en la Offhand (Para no perderla)
                if (mainHand.getType() == Material.IRON_SWORD) {
                    equip.setItemInOffHand(mainHand);
                }

                // 2. Generar Nuevo Arco (Recuperando encantamientos según dificultad)
                ItemStack newBow = new ItemStack(Material.BOW);
                // Pasamos una espada dummy porque solo queremos encantar el arco
                MilitaryMobHandler.applyEnchantments(ranger.getWorld().getDifficulty(), newBow,
                        new ItemStack(Material.IRON_SWORD));

                // 3. Equipar Arco en Principal
                equip.setItemInMainHand(newBow);

                ranger.getWorld().playSound(ranger.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1.0f, 1.5f);
            }
        }
    }
}
