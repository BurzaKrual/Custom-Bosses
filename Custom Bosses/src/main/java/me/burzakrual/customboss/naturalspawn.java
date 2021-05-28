package me.burzakrual.customboss;


import org.bukkit.event.EventHandler;
import me.burzakrual.customboss.mobs.bob;
import org.bukkit.entity.Zombie;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import java.util.Random;
import org.bukkit.entity.Spider;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.Listener;

public class naturalspawn implements Listener
{
    private main plugin;

    public naturalspawn(final main instance) {
        this.plugin = instance;
    }

    @EventHandler
    public void onCreatureSpawn(final CreatureSpawnEvent e) {
        if ((e.getSpawnReason().equals((Object)CreatureSpawnEvent.SpawnReason.NATURAL) || e.getSpawnReason().equals((Object)CreatureSpawnEvent.SpawnReason.SPAWNER_EGG) || e.getSpawnReason().equals((Object)CreatureSpawnEvent.SpawnReason.SPAWNER)) && this.plugin.getConfig().getBoolean("Configuration.NaturalSpawn.Enabled") && this.plugin.getConfig().getList("Configuration.NaturalSpawn.Worlds").contains(e.getEntity().getWorld().getName())) {
            if (e.getEntity() instanceof Zombie && this.plugin.getConfig().getBoolean("Configuration.Mobs.Bob.SpawnNatural")) {
                final Random random1 = new Random();
                final int number = random1.nextInt(101);
                final int rate = this.plugin.getConfig().getInt("Configuration.Mobs.Bob.Spawnrate");
                if (number <= rate) {
                    final bob mob16 = new bob(this.plugin);
                    mob16.spawn((CommandSender)Bukkit.getConsoleSender(), e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), e.getLocation().getWorld().getName());
                    e.setCancelled(true);
                }
            }
        }
    }
}