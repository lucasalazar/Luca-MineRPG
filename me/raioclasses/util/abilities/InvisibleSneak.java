package me.raioclasses.util.abilities;

import java.util.HashMap;
import java.util.UUID;

import me.raioclasses.Main;
import me.raioclasses.type.Scan;
import me.raioclasses.util.Config;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class InvisibleSneak
        implements Listener
{
    public static boolean check(Player player)
    {
        return Scan.classes.getAbility(player).contains("INVISIBLE_SNEAK");
    }

    HashMap<Player, Integer> cd = new HashMap();

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e)
    {
        final Player player = e.getPlayer();
        if (Config.players.yaml.contains(player.getUniqueId().toString())) {
            if (check(player))
            {
                if (!player.isSneaking()) {
                    if (!this.cd.containsKey(player)) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 120, 1));
                    }
                }
                if (player.isSneaking()) {
                    if (!this.cd.containsKey(player))
                    {
                        player.removePotionEffect(PotionEffectType.INVISIBILITY);

                        this.cd.put(player, Integer.valueOf(Config.setting.yaml.getInt("abilities.invisible_sneak.cooldown")));

                        new BukkitRunnable()
                        {
                            public void run()
                            {
                                if (((Integer)InvisibleSneak.this.cd.get(player)).intValue() > 0) {
                                    InvisibleSneak.this.cd.put(player, Integer.valueOf(((Integer)InvisibleSneak.this.cd.get(player)).intValue() - 1));
                                }
                                if (((Integer)InvisibleSneak.this.cd.get(player)).intValue() == 0)
                                {
                                    InvisibleSneak.this.cd.remove(player);

                                    player.sendMessage(ChatColor.YELLOW + "Você pode usar sua habilidade novamente.");

                                    cancel();
                                }
                            }
                        }.runTaskTimer(JavaPlugin.getPlugin(Main.class), 20L, 20L);
                    }
                    else
                    {
                        player.sendMessage(ChatColor.RED + "Essa habilidade está em cooldown por " + ChatColor.YELLOW + this.cd.get(player) + " segundos.");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onTryHit(EntityDamageByEntityEvent e)
    {

        if ((e.getDamager() instanceof Player))
        {
            Player player = (Player)e.getDamager();
            if (Config.players.yaml.contains(player.getUniqueId().toString())) {
                if (check(player)) {
                    if (player.isSneaking()) {
                        if (!Config.setting.yaml.getBoolean("abilities.invisible_sneak.invisible_hit"))
                        {
                            e.setCancelled(true);

                            player.sendMessage(ChatColor.RED + "Você não pode atacar jogadores enquanto estiver invisível.");
                        }
                    }
                }
            }
        }
    }
}
