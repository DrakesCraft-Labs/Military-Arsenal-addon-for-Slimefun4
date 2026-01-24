package com.Chagui68.weaponsaddon.items.machines.energy;

import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;

public class EnergyManager {

    public static int getCharge(Location loc) {
        String charge = BlockStorage.getLocationInfo(loc, "energy-charge");
        if (charge != null) {
            return Integer.parseInt(charge);
        }
        return 0;
    }

    public static void setCharge(Location loc, int charge) {
        BlockStorage.addBlockInfo(loc, "energy-charge", String.valueOf(charge));
    }

    public static boolean removeCharge(Location loc, int amount) {
        int current = getCharge(loc);
        if (current >= amount) {
            setCharge(loc, current - amount);
            return true;
        }
        return false;
    }

    public static void addCharge(Location loc, int amount) {
        int current = getCharge(loc);
        setCharge(loc, current + amount);
    }
}
