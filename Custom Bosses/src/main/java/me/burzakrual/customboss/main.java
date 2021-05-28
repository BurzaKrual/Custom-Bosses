package me.burzakrual.customboss;

import me.burzakrual.customboss.mobs.bob;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
    public final String VERSION = "1";

    configuration config = new configuration(this);

    public void onDisable() {
    }

    public void onEnable() {
        this.config.setupConfig();
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents((Listener)new bob(this), (Plugin)this);
        getCommand("custommobs").setExecutor(new commands(this));
        pm.registerEvents(new naturalspawn(this), (Plugin)this);
    }
}
