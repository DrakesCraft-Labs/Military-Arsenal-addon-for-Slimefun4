package com.Chagui68.weaponsaddon.items.machines;

import com.Chagui68.weaponsaddon.WeaponsAddon;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.util.Vector;

import java.util.Random;

public class AirstrikeExecutor {

    public static void executeBombardment(Location target, Player initiator) {
        World world = target.getWorld();
        Random random = new Random();

        int waves = 2;
        int bombsPerWave = 4;
        int delayBetweenWaves = 100;

        for (int wave = 0; wave < waves; wave++) {
            int waveDelay = wave * delayBetweenWaves;

            Bukkit.getScheduler().runTaskLater(WeaponsAddon.getInstance(), () -> {
                world.playSound(target, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 2.0f, 0.8f);

                for (int bomb = 0; bomb < bombsPerWave; bomb++) {
                    Bukkit.getScheduler().runTaskLater(WeaponsAddon.getInstance(), () -> {
                        double offsetX = (random.nextDouble() - 0.5) * 10;
                        double offsetZ = (random.nextDouble() - 0.5) * 10;

                        Location spawnLoc = target.clone().add(offsetX, 80, offsetZ);

                        TNTPrimed tnt = world.spawn(spawnLoc, TNTPrimed.class);
                        tnt.setFuseTicks(60);
                        tnt.setYield(4.0f);
                        tnt.setVelocity(new Vector(0, -1, 0));

                        // CORRECCIÓN: Usar partículas 1.20.6
                        world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, spawnLoc, 20, 0.5, 0.5, 0.5, 0.01);
                        world.playSound(spawnLoc, Sound.ENTITY_TNT_PRIMED, 1.0f, 0.6f);

                    }, bomb * 3L);
                }
            }, waveDelay);
        }

        initiator.sendMessage("§c§l[BOMBARDMENT] §7Impact in " + (waves * delayBetweenWaves / 20) + " seconds...");
    }
}
