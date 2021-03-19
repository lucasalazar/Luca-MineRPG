package me.raioclasses.util.stats;

import java.util.UUID;

import me.raioclasses.type.Scan;
import me.raioclasses.util.Config;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Damage
        implements Listener
{
    @EventHandler
    public void onTryhit(EntityDamageByEntityEvent e)
    {
        if ((e.getDamager() instanceof Player))
        {
            Player player = (Player)e.getDamager();
            if (Config.players.yaml.contains(player.getUniqueId().toString()))
            {
                double formula = e.getDamage() * (0.1D * Scan.classes.getDamage(player));

                e.setDamage(formula);
            }
        }
    }
}