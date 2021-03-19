package me.raioclasses.util.abilities;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import me.raioclasses.Main;
import me.raioclasses.type.Scan;
import me.raioclasses.util.Config;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class FireHand
        implements Listener
{
    public static boolean check(Player player)
    {
        return Scan.classes.getAbility(player).contains("FIRE_HAND");
    }

    HashMap<Player, Integer> cd = new HashMap();

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e)
    {
        if ((e.getDamager() instanceof Player))
        {
            final Player player = (Player)e.getDamager();
            if (Config.players.yaml.contains(player.getUniqueId().toString())) {
                if (check(player)) {
                    if (!this.cd.containsKey(player))
                    {
                        Random random = new Random();
                        int generatedValue = random.nextInt(Config.setting.yaml.getInt("abilities.fire_hand.chance")) + 1;
                        if (generatedValue == 1)
                        {
                            e.getEntity().setFireTicks(20 * Config.setting.yaml.getInt("abilities.fire_hand.duration"));
                            this.cd.put(player, Integer.valueOf(Config.setting.yaml.getInt("abilities.fire_hand.cooldown")));

                            new BukkitRunnable()
                            {
                                public void run()
                                {
                                    if (((Integer)FireHand.this.cd.get(player)).intValue() > 0) {
                                        FireHand.this.cd.put(player, Integer.valueOf(((Integer)FireHand.this.cd.get(player)).intValue() - 1));
                                    }
                                    if (((Integer)FireHand.this.cd.get(player)).intValue() == 0)
                                    {
                                        FireHand.this.cd.remove(player);

                                        cancel();
                                    }
                                }
                            }.runTaskTimer(JavaPlugin.getPlugin(Main.class), 20L, 20L);
                        }
                    }
                }
            }
        }
    }
}
