package me.raioclasses.util.abilities;

import java.util.HashMap;
import java.util.Random;

import me.raioclasses.Main;
import me.raioclasses.type.Scan;
import me.raioclasses.util.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class PoisonHand
        implements Listener
{
    public static boolean check(Player player)
    {
        return Scan.classes.getAbility(player).contains("POISON_HAND");
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
                        int generatedValue = random.nextInt(Config.setting.yaml.getInt("abilities.poison_hand.chance")) + 1;
                        if (generatedValue == 1)
                        {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * Config.setting.yaml.getInt("abilities.poison_hand.duration"), 0));
                            this.cd.put(player, Integer.valueOf(Config.setting.yaml.getInt("abilities.poison_hand.cooldown")));

                            new BukkitRunnable()
                            {
                                public void run()
                                {
                                    if (((Integer)PoisonHand.this.cd.get(player)).intValue() > 0) {
                                        PoisonHand.this.cd.put(player, Integer.valueOf(((Integer)PoisonHand.this.cd.get(player)).intValue() - 1));
                                    }
                                    if (((Integer)PoisonHand.this.cd.get(player)).intValue() == 0)
                                    {
                                        PoisonHand.this.cd.remove(player);

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
