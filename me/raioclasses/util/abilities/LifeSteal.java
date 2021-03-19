package me.raioclasses.util.abilities;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import me.raioclasses.Main;
import me.raioclasses.type.Scan;
import me.raioclasses.util.Config;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class LifeSteal
        implements Listener
{
    public static boolean check(Player player)
    {
        return Scan.classes.getAbility(player).contains("LIFE_STEAL");
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
                        int generatedValue = random.nextInt(Config.setting.yaml.getInt("abilities.life_steal.chance")) + 1;
                        if (generatedValue == 1)
                        {
                            double dmg = e.getDamage();
                            if (player.getHealth() + dmg > player.getMaxHealth()) {
                                player.setHealth(player.getMaxHealth());
                            }
                            if (player.getHealth() + dmg <= player.getMaxHealth()) {
                                player.setHealth(player.getHealth() + dmg);
                            }
                            this.cd.put(player, Integer.valueOf(Config.setting.yaml.getInt("abilities.life_steal.cooldown")));

                            new BukkitRunnable()
                            {
                                public void run()
                                {
                                    if (((Integer)LifeSteal.this.cd.get(player)).intValue() > 0) {
                                        LifeSteal.this.cd.put(player, Integer.valueOf(((Integer)LifeSteal.this.cd.get(player)).intValue() - 1));
                                    }
                                    if (((Integer)LifeSteal.this.cd.get(player)).intValue() == 0)
                                    {
                                        LifeSteal.this.cd.remove(player);

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
