package com.Chagui68.weaponsaddon.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.core.handlers.EntityInteractHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AntimatterRifle extends SlimefunItem {

    public static final SlimefunItemStack ANTIMATTER_RIFLE = new SlimefunItemStack(
            "ANTIMATTER_RIFLE",
            Material.NETHERITE_HOE,
            "&4☢ &cAntimatter Rifle",
            "",
            "&7Ultimate annihilation weapon",
            "&7Destroys any entity instantly",
            "",
            "&6Damage: &c999,999 HP (Instant Kill)",
            "&6Range: &eMelee",
            "&6Cooldown: &eNone",
            "",
            "&c⚠ EXTREME DANGER",
            "&c⚠ Use with caution",
            "",
            "&eRight-Click entity to annihilate"
    );

    static {
        ItemMeta meta = ANTIMATTER_RIFLE.getItemMeta();
        if (meta != null) {
            meta.setUnbreakable(true);
            ANTIMATTER_RIFLE.setItemMeta(meta);
        }
    }

    public AntimatterRifle(ItemGroup itemGroup, SlimefunItemStack item) {
        super(itemGroup, item, MilitaryRecipeTypes.MILITARY_MACHINE_FABRICATOR, new ItemStack[9]);
    }

    @Override
    public void preRegister() {
        addItemHandler((EntityInteractHandler) (e, item, offhand) -> {
            if (e.getRightClicked() instanceof LivingEntity) {
                LivingEntity target = (LivingEntity) e.getRightClicked();
                Player p = e.getPlayer();

                target.getWorld().spawnParticle(Particle.EXPLOSION, target.getLocation(), 50, 1, 1, 1, 0.3);
                target.getWorld().spawnParticle(Particle.FLASH, target.getLocation(), 10);
                target.getWorld().playSound(target.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 2.0f, 0.5f);

                target.damage(999999, p);

                p.sendMessage(ChatColor.DARK_RED + "☢ " + ChatColor.RED + "ANTIMATTER ANNIHILATION!");
                p.sendMessage(ChatColor.GRAY + "Target eliminated instantly");

                e.setCancelled(true);
            }
        });
    }

    public static void register(SlimefunAddon addon, ItemGroup category) {
        new AntimatterRifle(category, ANTIMATTER_RIFLE).register(addon);
    }
}
