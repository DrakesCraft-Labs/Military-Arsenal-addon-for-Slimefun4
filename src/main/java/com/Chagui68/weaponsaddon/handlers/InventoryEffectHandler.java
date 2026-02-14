package com.Chagui68.weaponsaddon.handlers;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import com.Chagui68.weaponsaddon.WeaponsAddon;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static org.bukkit.Bukkit.getOnlinePlayers;

public class InventoryEffectHandler extends BukkitRunnable {

    @Override
    public void run() {
        if (!WeaponsAddon.getInstance().getConfig()
                .getBoolean("inventory-effects.tungsten-weight.enabled", true)) {
            return;
        }

        /*
         * Lo que aparece a continuacion sirve para definir el tiempo de un efecto en
         * base a un intervalo
         * En este caso se esta evaluando si un jugador tiene un item (Lingote de
         * tugsteno) y cada 3 segudos
         * que hace la revicion esta evaluando si lo tiene le aplica el efecto si no lo
         * tiene no se lo aplica.
         * 
         * A su vez se le suma el intervalo y 40 ticks (2 segundo) para aplicar un
         * efecto de lentitud de 5 segudos
         */

        int slownessLevel = WeaponsAddon.getInstance().getConfig()
                .getInt("inventory-effects.tungsten-weight.slowness-level", 2);
        int interval = WeaponsAddon.getInstance().getConfig()
                .getInt("inventory-effects.tungsten-weight.check-interval", 60);
        int duration = interval + 40;

        for (Player player : getOnlinePlayers()) {
            int finalLevel = getTungstenSlownessLevel(player, slownessLevel);
            if (finalLevel >= 0) {
                player.addPotionEffect(
                        new PotionEffect(PotionEffectType.SLOWNESS, duration, finalLevel, true, false, true));
            }
        }
    }

    private int getTungstenSlownessLevel(Player player, int baseLevel) {
        int maxLevel = -1;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item == null)
                continue;

            SlimefunItem sfItem = SlimefunItem.getByItem(item);
            if (sfItem != null) {
                String id = sfItem.getId();
                if (id.equals("MA_TUNGSTEN_INGOT")) {
                    maxLevel = Math.max(maxLevel, baseLevel);
                } else if (id.equals("MA_TUNGSTEN_BLADE")) {
                    maxLevel = Math.max(maxLevel, baseLevel + 1); // +1 higher than the ingot
                }
            }
        }
        return maxLevel;
    }
}
