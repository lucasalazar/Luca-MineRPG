package me.raioclasses.type;

import me.raioclasses.util.Config;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Scan
{
    public static class classes
    {
        public static void set(Player player, String className)
        {
            Config.players.yaml.set(player.getUniqueId() + ".class", className);
            Config.players.save();
        }

        public static String getPrefix(Player player)
        {
            String playerClass = Config.players.yaml.getString(player.getUniqueId() + ".class");

            return Config.classes.yaml.getString("classes." + playerClass + ".prefix");
        }

        public static String getSuffix(Player player)
        {
            String playerClass = Config.players.yaml.getString(player.getUniqueId() + ".class");

            return Config.classes.yaml.getString("classes." + playerClass + ".suffix");
        }

        public static int getDamage(Player player)
        {
            String playerClass = Config.players.yaml.getString(player.getUniqueId() + ".class");

            return Config.classes.yaml.getInt("classes." + playerClass + ".damage");
        }

        public static double getDefense(Player player)
        {
            String playerClass = Config.players.yaml.getString(player.getUniqueId() + ".class");

            return Config.classes.yaml.getDouble("classes." + playerClass + ".defense");
        }

        public static float getSpeed(Player player)
        {
            String playerClass = Config.players.yaml.getString(player.getUniqueId() + ".class");

            return Config.classes.yaml.getInt("classes." + playerClass + ".speed");
        }

        public static float getMaxHealth(Player player)
        {
            String playerClass = Config.players.yaml.getString(player.getUniqueId() + ".class");

            return Config.classes.yaml.getInt("classes." + playerClass + ".health");
        }

        public static String getAbility(Player player)
        {
            String playerClass = Config.players.yaml.getString(player.getUniqueId() + ".class");

            return Config.classes.yaml.getString("classes." + playerClass + ".ability");
        }
    }
}
