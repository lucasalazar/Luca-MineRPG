package me.raioclasses.events;

import java.util.UUID;

import me.raioclasses.type.Scan;
import me.raioclasses.util.Config;
import me.raioclasses.util.stats.Health;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinAndQuit
        implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player player = e.getPlayer();

        float speed = Scan.classes.getSpeed(player);
        if (Config.players.yaml.contains(player.getUniqueId().toString()))
        {
            player.setWalkSpeed(0.01F * speed);
            player.setMaxHealth(Scan.classes.getMaxHealth(player));
            player.setHealth(Health.get(player));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        Player player = e.getPlayer();

        player.setWalkSpeed(0.2F);
        player.setMaxHealth(20.0D);
        Health.set(player);
    }
}
