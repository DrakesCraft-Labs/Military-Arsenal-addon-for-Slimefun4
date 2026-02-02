package com.Chagui68.weaponsaddon.items.machines;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class AirstrikeExecutor {

    public static void executeBombardment(Location target, Player p) {
        JavaPlugin plugin = JavaPlugin.getProvidingPlugin(AirstrikeExecutor.class);

        new BukkitRunnable() {
            int wave = 0;

            @Override
            public void run() {
                if (wave >= 2) {
                    p.sendMessage(ChatColor.GREEN + "✓ [Terminal] Bombardment complete");
                    cancel();
                    return;
                }

                wave++;
                p.sendMessage(ChatColor.DARK_RED + "💣 [Terminal] Wave " + wave + "/2 - Bombs away!");

                for (int i = 0; i < 4; i++) {
                    double offsetX = (Math.random() - 0.5) * 10;
                    double offsetZ = (Math.random() - 0.5) * 10;
                    Location bombLoc = target.clone().add(offsetX, 50, offsetZ);

                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        target.getWorld().playSound(bombLoc, Sound.ENTITY_WITHER_SHOOT, 3.0f, 0.5f);
                        target.getWorld().spawnParticle(Particle.EXPLOSION, bombLoc, 10, 2, 2, 2, 0.1);

                        TNTPrimed tnt = target.getWorld().spawn(bombLoc, TNTPrimed.class);
                        tnt.setFuseTicks(60);
                        tnt.setVelocity(new Vector(0, -1, 0));

                    }, i * 10L);
                }
            }
        }.runTaskTimer(plugin, 60L, 80L);
    }
}
