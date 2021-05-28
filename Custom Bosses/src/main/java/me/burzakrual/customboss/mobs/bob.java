package me.burzakrual.customboss.mobs;


import org.bukkit.event.EventHandler;
import org.bukkit.Effect;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import java.util.Iterator;
import org.bukkit.World;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.ArrayList;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.Location;
import org.bukkit.entity.Zombie;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import me.burzakrual.customboss.main;
import org.bukkit.event.Listener;

public class bob implements Listener
{
    private main plugin;

    public bob(final main instance) {
        this.plugin = instance;
    }

    public boolean spawn(final CommandSender cs, final double x, final double y, final double z, final String worldname) {
        final ItemStack item0 = new ItemStack(Material.GOLDEN_HOE);
        final ItemMeta meta0 = item0.getItemMeta();
        meta0.setDisplayName("§eMagicWand");
        meta0.addEnchant(Enchantment.KNOCKBACK, 2, false);
        meta0.addEnchant(Enchantment.DURABILITY, 3, false);
        meta0.addEnchant(Enchantment.FIRE_ASPECT, 1, false);
        meta0.addEnchant(Enchantment.DAMAGE_UNDEAD, 5, false);
        item0.setItemMeta(meta0);
        if (cs instanceof Player) {
            final Player player = (Player)cs;
            if (!player.hasPermission("insanemobs.mob.bob") && !player.hasPermission("custombosses.*")) {
                cs.sendMessage(this.plugin.getConfig().getString("Configuration.Messages.NoPermission").replace("&", "§"));
                return true;
            }
        }
        final World world = Bukkit.getWorld(worldname);
        final Zombie Bob = (Zombie)world.spawn(new Location(world, x, y, z), (Class)Zombie.class);
        if (!this.plugin.getConfig().getString("Configuration.Mobs.Bob.Displayname").equalsIgnoreCase("$none")) {
            Bob.setCustomName(this.plugin.getConfig().getString("Configuration.Mobs.Bob.Displayname").replace('&', '§').replace("\\", ""));
        }
        Bob.setCustomNameVisible(false);
        Bob.setMetadata("Bob", (MetadataValue)new FixedMetadataValue((Plugin)this.plugin, (Object)true));
        Bob.setMaxHealth((double)this.plugin.getConfig().getInt("Configuration.Mobs.Bob.Health"));
        Bob.setHealth((double)this.plugin.getConfig().getInt("Configuration.Mobs.Bob.Health"));
        Bob.getEquipment().setHelmet(new ItemStack(Material.SKELETON_SKULL));
        Bob.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        Bob.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
        Bob.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
        Bob.getEquipment().setItemInHand(item0);
        final ArrayList<String> effectlist = (ArrayList<String>)this.plugin.getConfig().getList("Configuration.Mobs.Bob.Effects");
        for (final String effect : effectlist) {
            final String effectname = effect.split(",")[0];
            final int effectvalue = Integer.parseInt(effect.split(",")[1]);
            if (effectname.equalsIgnoreCase("Strength")) {
                Bob.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200000000, effectvalue));
            }
            else if (effectname.equalsIgnoreCase("Speed")) {
                Bob.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200000000, effectvalue));
            }
            else if (effectname.equalsIgnoreCase("Slow")) {
                Bob.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200000000, effectvalue));
            }
            else if (effectname.equalsIgnoreCase("Jump")) {
                Bob.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200000000, effectvalue));
            }
            else if (effectname.equalsIgnoreCase("Resistance")) {
                Bob.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200000000, effectvalue));
            }
            else if (effectname.equalsIgnoreCase("Regeneration")) {
                Bob.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200000000, effectvalue));
            }
            else if (effectname.equalsIgnoreCase("FireResistance")) {
                Bob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200000000, effectvalue));
            }
            else if (effectname.equalsIgnoreCase("WaterBreathing")) {
                Bob.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 200000000, effectvalue));
            }
            else if (effectname.equalsIgnoreCase("Invisibility")) {
                Bob.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200000000, effectvalue));
            }
            else {
                if (!effectname.equalsIgnoreCase("Weakness")) {
                    continue;
                }
                Bob.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200000000, effectvalue));
            }
        }
        if (cs instanceof Player) {
            cs.sendMessage(this.plugin.getConfig().getString("Configuration.Messages.MobSpawned").replace("%mob%", this.plugin.getConfig().getString("Configuration.Mobs.Bob.Displayname")).replace("&", "§"));
        }
        return true;
    }

    @EventHandler
    public void onEntityDamage(final EntityDamageByEntityEvent e) {
        final ItemStack item0 = new ItemStack(Material.GOLDEN_HOE);
        final ItemMeta meta0 = item0.getItemMeta();
        meta0.setDisplayName("§eMagicWand");
        meta0.addEnchant(Enchantment.KNOCKBACK, 2, false);
        meta0.addEnchant(Enchantment.DURABILITY, 3, false);
        meta0.addEnchant(Enchantment.FIRE_ASPECT, 1, false);
        meta0.addEnchant(Enchantment.DAMAGE_UNDEAD, 5, false);
        item0.setItemMeta(meta0);
        if (!(e.getEntity() instanceof LivingEntity)) {
            return;
        }
        if (e.getEntity() instanceof Zombie) {
            final Zombie zomb = (Zombie)e.getEntity();
            if (zomb.hasMetadata("Bob") && this.plugin.getConfig().getBoolean("Configuration.Mobs.Bob.Spawn_Minions")) {
                final int a = (int)(Math.random() * 2.0 + 1.0);
                if (a == 1) {
                    final Zombie minion = (Zombie)e.getEntity().getWorld().spawn(e.getEntity().getLocation(), (Class)Zombie.class);
                    minion.setBaby(true);
                }
            }
        }
        else if (e.getEntity() instanceof Player) {
            final Player p = (Player)e.getEntity();
            if (p.getItemInHand().equals((Object)item0) && e.getDamager() instanceof LivingEntity) {
                final LivingEntity ent = (LivingEntity)e.getDamager();
                ent.damage(3.0);
                p.getWorld().playEffect(ent.getLocation(), Effect.ENDER_SIGNAL, 5);
            }
        }
    }
}

