package com.Chagui68.weaponsaddon.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main command for WeaponsAddon.
 * Usage: /weapons delete <arena|turrets>
 */
public class WeaponsCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label,
            String[] args) {
        if (!sender.hasPermission("militaryarsenal.admin")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }

        if (args.length < 2 || (!args[0].equalsIgnoreCase("delete") && !args[0].equalsIgnoreCase("summon"))) {
            sender.sendMessage(ChatColor.RED + "Usage: /weapons <delete|summon> <args>");
            return true;
        }

        String cmdType = args[0].toLowerCase();

        switch (cmdType) {
            case "delete":
                String deleteType = args[1].toLowerCase();
                if (deleteType.equals("arena")) {
                    try {
                        com.Chagui68.weaponsaddon.listeners.BossAIHandler.destroyArena();
                        sender.sendMessage(ChatColor.GREEN + "✓ Arena has been successfully reset!");
                    } catch (Exception e) {
                        sender.sendMessage(ChatColor.RED + "Error resetting arena: " + e.getMessage());
                    }
                } else if (deleteType.equals("turrets")) {
                    // Logic for turrets... (Keeping it simple for now as it's a large block)
                    sender.sendMessage(ChatColor.YELLOW + "Turret cleanup logic integration...");
                }
                break;

            case "summon":
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ChatColor.RED + "Only players can use this command!");
                    return true;
                }
                if (args.length < 2) {
                    sender.sendMessage(ChatColor.RED + "Usage: /weapons summon <mob_type>");
                    return true;
                }

                Player p = (Player) sender;
                String mobType = args[1].toLowerCase();
                Location loc = p.getLocation();
                org.bukkit.World world = loc.getWorld();

                try {
                    switch (mobType) {
                        case "king":
                            org.bukkit.entity.ZombieVillager king = (org.bukkit.entity.ZombieVillager) world
                                    .spawnEntity(loc, org.bukkit.entity.EntityType.ZOMBIE_VILLAGER);
                            com.Chagui68.weaponsaddon.handlers.MilitaryMobHandler.equipKing(king);
                            sender.sendMessage(ChatColor.GREEN + "✓ Summoned The King!");
                            break;
                        case "warrior":
                            org.bukkit.entity.Zombie warrior = (org.bukkit.entity.Zombie) world.spawnEntity(loc,
                                    org.bukkit.entity.EntityType.ZOMBIE);
                            com.Chagui68.weaponsaddon.handlers.MilitaryMobHandler.equipWarrior(warrior);
                            sender.sendMessage(ChatColor.GREEN + "✓ Summoned a Warrior!");
                            break;
                        case "pusher":
                            org.bukkit.entity.Zombie pusher = (org.bukkit.entity.Zombie) world.spawnEntity(loc,
                                    org.bukkit.entity.EntityType.ZOMBIE);
                            com.Chagui68.weaponsaddon.handlers.MilitaryMobHandler.equipPusher(pusher);
                            sender.sendMessage(ChatColor.GREEN + "✓ Summoned a Pusher!");
                            break;
                        case "elite_killer":
                            org.bukkit.entity.Zombie killer = (org.bukkit.entity.Zombie) world.spawnEntity(loc,
                                    org.bukkit.entity.EntityType.ZOMBIE);
                            com.Chagui68.weaponsaddon.handlers.MilitaryMobHandler.equipEliteKiller(killer);
                            sender.sendMessage(ChatColor.GREEN + "✓ Summoned an Elite Killer!");
                            break;
                        case "heavy_gunner":
                            org.bukkit.entity.Skeleton boss = (org.bukkit.entity.Skeleton) world.spawnEntity(loc,
                                    org.bukkit.entity.EntityType.SKELETON);
                            com.Chagui68.weaponsaddon.handlers.MilitaryMobHandler.equipHeavyGunner(boss);
                            sender.sendMessage(ChatColor.GREEN + "✓ Summoned the Heavy Gunner boss!");
                            break;
                        case "elite_ranger":
                            org.bukkit.entity.Skeleton ranger = (org.bukkit.entity.Skeleton) world.spawnEntity(loc,
                                    org.bukkit.entity.EntityType.SKELETON);
                            com.Chagui68.weaponsaddon.handlers.MilitaryMobHandler.equipEliteRanger(ranger);
                            sender.sendMessage(ChatColor.GREEN + "✓ Summoned an Elite Ranger!");
                            break;
                        case "battle_witch":
                            org.bukkit.entity.Witch witch = (org.bukkit.entity.Witch) world.spawnEntity(loc,
                                    org.bukkit.entity.EntityType.WITCH);
                            com.Chagui68.weaponsaddon.handlers.MilitaryMobHandler.equipBattleWitch(witch);
                            sender.sendMessage(ChatColor.GREEN + "✓ Summoned a Battle Witch!");
                            break;
                        default:
                            sender.sendMessage(ChatColor.RED
                                    + "Unknown mob type. Use: king, warrior, pusher, elite_killer, heavy_gunner, elite_ranger, battle_witch.");
                            break;
                    }
                } catch (Exception e) {
                    sender.sendMessage(ChatColor.RED + "Error summoning " + mobType + ": " + e.getMessage());
                }
                break;

            default:
                sender.sendMessage(ChatColor.RED + "Unknown type. Use 'delete' or 'summon'.");
                break;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias,
            String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            completions.add("delete");
            completions.add("summon");
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("delete")) {
                completions.add("arena");
                completions.add("turrets");
            } else if (args[0].equalsIgnoreCase("summon")) {
                completions.add("king");
                completions.add("warrior");
                completions.add("pusher");
                completions.add("elite_killer");
                completions.add("heavy_gunner");
                completions.add("elite_ranger");
                completions.add("battle_witch");
            }
        }

        return completions.stream()
                .filter(s -> s.toLowerCase().startsWith(args[args.length - 1].toLowerCase()))
                .collect(Collectors.toList());
    }
}
