package com.Chagui68.weaponsaddon.utils;

import org.bukkit.Location;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/**
 * Utility class to provide robust protection against turret duplication.
 */
public class TurretUtils {

    // Thread-safe map to store location-based dismantle locks
    // Key: Block Location, Value: System time in ms when the lock expires
    private static final Map<Location, Long> DISMANTLE_LOCKS = new ConcurrentHashMap<>();
    private static final long LOCK_DURATION = 1000; // 1 second lock

    /**
     * Attempts to acquire a dismantle lock for a specific location.
     * 
     * @param loc The location to lock.
     * @return true if the lock was successfully acquired (this thread can proceed),
     *         false if the location is already being dismantled.
     */
    public static boolean beginDismantle(Location loc) {
        if (loc == null)
            return false;

        // Clean up block coordinates to ensure consistent hashing
        Location blockLoc = loc.getBlock().getLocation();
        long now = System.currentTimeMillis();

        // Check existing lock
        Long lockTime = DISMANTLE_LOCKS.get(blockLoc);
        if (lockTime != null && now < lockTime) {
            return false; // Already locked
        }

        // Acquire lock
        DISMANTLE_LOCKS.put(blockLoc, now + LOCK_DURATION);

        // Optional: Periodically clean the map (though for its size it's usually fine)
        if (DISMANTLE_LOCKS.size() > 500) {
            DISMANTLE_LOCKS.entrySet().removeIf(entry -> System.currentTimeMillis() > entry.getValue());
        }

        return true;
    }

    /**
     * Manually releases a lock (optional but good for testing).
     */
    public static void releaseLock(Location loc) {
        if (loc != null) {
            DISMANTLE_LOCKS.remove(loc.getBlock().getLocation());
        }
    }
}
