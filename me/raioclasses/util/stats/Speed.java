package me.raioclasses.util.stats;

import java.util.UUID;

import me.raioclasses.type.Scan;
import me.raioclasses.util.Config;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class Speed
        implements Listener
{
    public static void set(Player player)
    {
        if (Config.players.yaml.contains(player.getUniqueId().toString()))
        {
            float speed = Scan.classes.getSpeed(player);

            player.setWalkSpeed(0.01F * speed);
        }
    }

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent e)
    {
        Player player = e.getPlayer();
        if (!Config.players.yaml.contains(player.getUniqueId().toString())) {
            set(player);
        }
    }
}
