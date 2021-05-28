package me.burzakrual.customboss;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class configuration {
    static ArrayList<String> worlds = new ArrayList<>();

    static ArrayList<String> bobdrops = new ArrayList<>();

    static ArrayList<String> bobeffects = new ArrayList<>();


    private main plugin;

    public configuration(main instance) {
        this.plugin = instance;
    }

    public void setupConfig() {
        try {
            File file1 = new File("plugins/CustomBossess", "config.yml");
            this.plugin.getClass();
            if (file1.exists() && !this.plugin.getConfig().options().header().equalsIgnoreCase("CustomBossess v" + "1" + " by BurzaKrual")) {
                file1.delete();
                this.plugin.reloadConfig();
            }
        } catch (Exception exception) {}
        File file = new File("plugins/CustomBossess", "config.yml");
        this.plugin.getClass();
        this.plugin.getConfig().options().header("CustomBossess v" + "1" + " by BurzaKrual");
        this.plugin.getConfig().addDefault("Configuration.NaturalSpawn.Enabled", Boolean.valueOf(true));
        this.plugin.getConfig().addDefault("Configuration.NaturalSpawn.Worlds", worlds);
        this.plugin.getConfig().addDefault("Configuration.Mobs.Bob", "");
        this.plugin.getConfig().addDefault("Configuration.Mobs.Bob.Health", Integer.valueOf(20));
        this.plugin.getConfig().addDefault("Configuration.Mobs.Bob.Spawnrate", Integer.valueOf(10));
        this.plugin.getConfig().addDefault("Configuration.Mobs.Bob.Displayname", "Bob");
        this.plugin.getConfig().addDefault("Configuration.Mobs.Bob.Spawn_Minions", Boolean.valueOf(true));
        this.plugin.getConfig().addDefault("Configuration.Mobs.Bob.SpawnNatural", Boolean.valueOf(true));
        this.plugin.getConfig().addDefault("Configuration.Mobs.Bob.Effects", bobeffects);
        this.plugin.getConfig().addDefault("Configuration.Mobs.Bob.Drops", bobdrops);
        this.plugin.getConfig().addDefault("Configuration.Messages.InvalidSender", "Invalid sender!");
        this.plugin.getConfig().addDefault("Configuration.Messages.ZeroArguments", "&6Type &c/CustomBossess help &6for a list of commands.");
        this.plugin.getConfig().addDefault("Configuration.Messages.NoPermission", "&7[&cCustomBossess&42&7] &4You do not have permission to run this command!");
        this.plugin.getConfig().addDefault("Configuration.Messages.InvalidArgument", "&7[&cCustomBossess&42&7] &cInvalid argument! &6/CustomBossess help");
        this.plugin.getConfig().addDefault("Configuration.Messages.MobSpawned", "&7[&cCustomBossess&42&7] &6Spawned &c%mob% &6.");
        this.plugin.getConfig().addDefault("Configuration.Messages.InvalidMob", "&7[&cCustomBossess&42&7] &cMob does not exist!");
        this.plugin.getConfig().addDefault("Configuration.Messages.ReloadedConfig", "&7[&cCustomBossess&42&7] &6Config reloaded.");
        System.out.println("This if is true");
        if (worlds.isEmpty()) {
            worlds.add("world");
            worlds.add("world_nether");
            worlds.add("world_the_end");
        }
        if (bobeffects.isEmpty()) {
            bobeffects.add("Slow,1");
            bobeffects.add("FireResistance,1");
        }
        if (bobdrops.isEmpty())
            bobdrops.add("369,0,2,40");
        this.plugin.getConfig().options().copyDefaults(true);
        try {
            this.plugin.getConfig().save(file);
        } catch (IOException iOException) {}
    }
}
