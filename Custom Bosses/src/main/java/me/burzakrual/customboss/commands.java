package me.burzakrual.customboss;


import java.util.Set;

import me.burzakrual.customboss.mobs.bob;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;


public class commands implements CommandExecutor {
    private main plugin;

    public commands(final main instance) {
        this.plugin = instance;
    }

    public boolean onCommand(final CommandSender cs, final Command cmd, final String label, final String[] args) {
        if (cs instanceof Player) {
            final Player player = (Player) cs;
            if (args.length == 0) {
                cs.sendMessage(this.plugin.getConfig().getString("Configuration.Messages.ZeroArguments").replace("&", "§"));
            } else if (args.length == 1) {
                final double x = player.getTargetBlock((Set) null, 50).getLocation().getX();
                final double y = player.getTargetBlock((Set) null, 50).getLocation().getY() + 1.0;
                final double z = player.getTargetBlock((Set) null, 50).getLocation().getZ();
                if (args[0].equalsIgnoreCase("bob") || args[0].equalsIgnoreCase(this.plugin.getConfig().getString("Configuration.Mobs.bob.Displayname"))) {
                    final bob mob12 = new bob(this.plugin);
                    mob12.spawn(cs, x, y, z, player.getWorld().getName());
                }
            } else if (args[0].equalsIgnoreCase("help")) {
                if (cs instanceof Player) {
                    final Player p = (Player) cs;
                    if (!p.hasPermission("CustomBosses.help") && !p.hasPermission("CustomBosses.*")) {
                        cs.sendMessage(this.plugin.getConfig().getString("Configuration.Messages.NoPermission").replace("&", "§"));
                        return true;
                    }
                }
                cs.sendMessage("");
                cs.sendMessage("");
                cs.sendMessage("§8*****************§7[§cCustomBosses§42§7]§8*****************");
                cs.sendMessage("§c/CustomBosses <mob> §6to spawn a given mob");
                cs.sendMessage("§c/CustomBosses list §6to show a list of all the mobs");
                cs.sendMessage("§c/CustomBosses reload §6to reload the config.yml file");
                cs.sendMessage("§c/CustomBosses help §6to display this page");
                cs.sendMessage("§8*****************§cby BurzaKrual §8*****************");
            } else if (args[0].equalsIgnoreCase("list")) {
                if (cs instanceof Player) {
                    final Player p = (Player) cs;
                    if (!p.hasPermission("CustomBosses.list") && !p.hasPermission("CustomBosses.*")) {
                        cs.sendMessage(this.plugin.getConfig().getString("Configuration.Messages.NoPermission").replace("&", "§"));
                        return true;
                    }
                }
                cs.sendMessage("");
                cs.sendMessage("");
                cs.sendMessage("§8*****************§7[§cCustomBosses§42§7]§8****************");
                cs.sendMessage("§7Original name : Displayname");
                cs.sendMessage("§cBob §6: " + this.plugin.getConfig().getString("Configuration.Mobs.Bob.Displayname"));

                cs.sendMessage("§6/custommobs <mob>");
            } else {
                if (!args[0].equalsIgnoreCase("reload")) {
                    cs.sendMessage(this.plugin.getConfig().getString("Configuration.Messages.InvalidMob").replace("&", "§"));
                    return true;
                }
                if (cs instanceof Player) {
                    final Player p = (Player) cs;
                    if (!p.hasPermission("CustomBosses.reload") && !p.hasPermission("CustomBosses.*")) {
                        cs.sendMessage(this.plugin.getConfig().getString("Configuration.Messages.NoPermission").replace("&", "§"));
                        return true;
                    }
                }
                this.plugin.reloadConfig();
                cs.sendMessage("");
                cs.sendMessage(this.plugin.getConfig().getString("Configuration.Messages.ReloadedConfig").replace("&", "§"));
            }
        } else if (args.length > 1 && args.length < 5) {
            cs.sendMessage(this.plugin.getConfig().getString("Configuration.Messages.InvalidArgument").replace("&", "§"));
        }
        return true;
    }
}