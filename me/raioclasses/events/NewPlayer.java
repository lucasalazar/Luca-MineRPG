package me.raioclasses.events;

import java.util.UUID;

import me.raioclasses.util.Config;
import me.raioclasses.util.GUI;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class NewPlayer
        implements Listener
{
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e)
    {
        Config.players.load();

        Player player = e.getPlayer();
        if (!Config.players.yaml.contains(player.getUniqueId().toString())) {
            if (Config.setting.yaml.getBoolean("new_player.force_select_class")) {
                GUI.open(player);
            }
        }
    }
}
