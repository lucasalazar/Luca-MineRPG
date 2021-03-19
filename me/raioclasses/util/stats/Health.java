package me.raioclasses.util.stats;

import me.raioclasses.util.Config;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Health
        implements Listener
{
    public static void set(Player player)
    {
        Config.players.yaml.set(player.getUniqueId() + ".health", Double.valueOf(player.getHealth()));
        Config.players.save();
    }

    public static int get(Player player)
    {
        return Config.players.yaml.getInt(player.getUniqueId() + ".health");
    }
}
