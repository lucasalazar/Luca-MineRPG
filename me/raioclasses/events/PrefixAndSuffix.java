package me.raioclasses.events;

import java.util.UUID;

import me.raioclasses.type.Scan;
import me.raioclasses.util.Config;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PrefixAndSuffix
        implements Listener
{
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e)
    {
        Player player = e.getPlayer();
        if (Config.players.yaml.contains(player.getUniqueId().toString()))
        {
            if ((Config.setting.yaml.getBoolean("chat.prefix")) && (Config.setting.yaml.getBoolean("chat.suffix"))) {
                e.setFormat(ChatColor.translateAlternateColorCodes('&', Scan.classes.getPrefix(player)) + " " + ChatColor.RESET + "[" + player
                        .getDisplayName() + "]" + " " +
                        ChatColor.translateAlternateColorCodes('&', Scan.classes.getSuffix(player)) + " " + ChatColor.RESET + e.getMessage());
            }
            if ((Config.setting.yaml.getBoolean("chat.prefix")) && (!Config.setting.yaml.getBoolean("chat.suffix"))) {
                e.setFormat(ChatColor.translateAlternateColorCodes('&', Scan.classes.getPrefix(player)) + " " + ChatColor.RESET + e
                        .getFormat());
            }
            if ((!Config.setting.yaml.getBoolean("chat.prefix")) && (Config.setting.yaml.getBoolean("chat.suffix"))) {
                e.setFormat("[" + player.getDisplayName() + "]" + " " + ChatColor.translateAlternateColorCodes('&', Scan.classes.getSuffix(player)) + " " + ChatColor.RESET + e
                        .getMessage());
            }
        }
    }
}
