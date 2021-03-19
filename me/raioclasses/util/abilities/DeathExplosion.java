package me.raioclasses.util.abilities;

import java.util.UUID;

import me.raioclasses.type.Scan;
import me.raioclasses.util.Config;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.util.Vector;

public class DeathExplosion
        implements Listener
{
    public static boolean check(Player player)
    {
        return Scan.classes.getAbility(player).contains("DEATH_EXPLOSION");
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e)
    {
        if (e.getEntity().getKiller() != null)
        {
            Player player = e.getEntity();
            Player killer = e.getEntity().getKiller();
            if (Config.players.yaml.contains(player.getUniqueId().toString())) {
                if (check(player))
                {
                    double x = player.getLocation().getBlockX();
                    double y = player.getLocation().getBlockY();
                    double z = player.getLocation().getBlockZ();

                    player.getWorld().createExplosion(x, y, z, 0.0F, Config.setting.yaml
                            .getBoolean("abilities.death_explosion.fire_spread"), Config.setting.yaml
                            .getBoolean("abilities.death_explosion.block_damage"));

                    killer.setHealth(killer.getHealth() - Config.setting.yaml.getInt("abilities.death_explosion.damage"));

                    Vector unitVector = killer.getLocation().toVector().subtract(player.getLocation().toVector()).normalize();

                    killer.setVelocity(unitVector.multiply(2));
                }
            }
        }
    }
}
