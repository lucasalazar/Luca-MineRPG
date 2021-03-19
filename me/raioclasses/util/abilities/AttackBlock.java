package me.raioclasses.util.abilities;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import me.raioclasses.Main;
import me.raioclasses.type.Scan;
import me.raioclasses.util.Config;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class AttackBlock
        implements Listener
{
    public static boolean check(Player player)
    {
        return Scan.classes.getAbility(player).contains("ATTACK_BLOCK");
    }

    HashMap<Player, Integer> cd = new HashMap();

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e)
    {
        if ((e.getEntity() instanceof Player))
        {
            final Player player = (Player)e.getEntity();
            if (Config.players.yaml.contains(player.getUniqueId().toString())) {
                if (check(player)) {
                    if (!this.cd.containsKey(player))
                    {
                        Random random = new Random();
                        int generatedValue = random.nextInt(Config.setting.yaml.getInt("abilities.block.chance")) + 1;
                        if (generatedValue == 1)
                        {
                            e.setCancelled(true);
                            player.playSound(player.getLocation(), Sound.LEVEL_UP, 10.0F, 10.0F);
                            this.cd.put(player, Integer.valueOf(Config.setting.yaml.getInt("abilities.block.cooldown")));

                            new BukkitRunnable()
                            {
                                public void run()
                                {
                                    if (((Integer)AttackBlock.this.cd.get(player)).intValue() > 0) {
                                        AttackBlock.this.cd.put(player, Integer.valueOf(((Integer)AttackBlock.this.cd.get(player)).intValue() - 1));
                                    }
                                    if (((Integer)AttackBlock.this.cd.get(player)).intValue() == 0)
                                    {
                                        AttackBlock.this.cd.remove(player);

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