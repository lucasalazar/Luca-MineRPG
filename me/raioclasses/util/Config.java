package me.raioclasses.util;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class Config
{
    public static class setting
    {
        public static File file()
        {
            return new File(Bukkit.getPluginManager().getPlugin("RaioClasses").getDataFolder(), "setting.yml");
        }

        public static YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file());

        public static void load()
        {
            YamlConfiguration.loadConfiguration(file());
        }

        public static void save()
        {
            try
            {
                yaml.save(file());
            }
            catch (IOException localIOException) {}
        }

        public static void create()
        {
            if (!file().exists())
            {
                yaml.set("new_player.force_select_class", Boolean.valueOf(false));

                yaml.set("class_command_cooldown", Integer.valueOf(86400));

                yaml.set("chat.prefix", Boolean.valueOf(true));
                yaml.set("chat.suffix", Boolean.valueOf(false));

                yaml.set("abilities.invisible_sneak.invisible_hit", Boolean.valueOf(false));
                yaml.set("abilities.invisible_sneak.cooldown", Integer.valueOf(25));

                yaml.set("abilities.fire_hand.chance", Integer.valueOf(6));
                yaml.set("abilities.fire_hand.duration", Integer.valueOf(2));
                yaml.set("abilities.fire_hand.cooldown", Integer.valueOf(5));

                yaml.set("abilities.block.chance", Integer.valueOf(6));
                yaml.set("abilities.block.cooldown", Integer.valueOf(5));

                yaml.set("abilities.life_steal.chance", Integer.valueOf(8));
                yaml.set("abilities.life_steal.cooldown", Integer.valueOf(5));

                yaml.set("abilities.death_explosion.damage", Integer.valueOf(6));
                yaml.set("abilities.death_explosion.fire_spread", Boolean.valueOf(false));
                yaml.set("abilities.death_explosion.block_damage", Boolean.valueOf(false));

                yaml.set("abilities.poison_hand.cooldown", Integer.valueOf(15));
                yaml.set("abilities.poison_hand.duration", Integer.valueOf(2));
                yaml.set("abilities.poison_hand.chance", Integer.valueOf(6));

                save();
            }
        }
    }

    public static class classes
    {
        public static File file()
        {
            return new File(Bukkit.getPluginManager().getPlugin("RaioClasses").getDataFolder(), "classes.yml");
        }

        public static YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file());

        public static void load()
        {
            YamlConfiguration.loadConfiguration(file());
        }

        public static void save()
        {
            try
            {
                yaml.save(file());
            }
            catch (IOException localIOException) {}
        }

        public static void create()
        {
            if (!file().exists())
            {
                createClass("Cavaleiro", "&3[Cavaleiro]", "", 15.0D, 10.0D, 20.0D, 20, "DIAMOND_SWORD", 1, "ATTACK_BLOCK");
                createClass("Esquentadinho", "&3[Esquentadinho]", "", 10.0D, 10.0D, 20.0D, 20, "FLINT_AND_STEEL", 3, "FIRE_HAND");
                createClass("Assassino", "&3[Assassino]", "", 20.0D, 7.5D, 25.0D, 15, "CHAINMAIL_BOOTS", 5, "INVISIBLE_SNEAK");
                createClass("Vampiro", "&3[Vampiro]", "", 10.0D, 10.0D, 20.0D, 25, "REDSTONE", 7, "LIFE_STEAL");
                createClass("Mineiro", "&3[Mineiro]", "", 10.0D, 10.0D, 20.0D, 20, "DIAMOND_PICKAXE", 10, "FAST_MINER");
                createClass("Venenoso", "&3[Venenoso]", "", 15.0D, 10.0D, 20.0D, 20, "POTION", 12, "POISON_HAND");
                createClass("Ferreiro", "&3[Ferreiro]", "", 10D, 10D, 20D, 25, "ANVIL", 14, "REPAIR_HAND");
                createClass("Alquimista", "&3[Alquimista]", "", 10D, 10D, 20D, 25, "STICK", 16, "MAGICIAN_HAND");
                save();
            }
        }

        public static void createClass(String classes, String prefix, String suffix, double damage, double defense, double speed, int health, String icon, int slot, String ability)
        {
            yaml.set("classes." + classes + ".prefix", prefix);
            yaml.set("classes." + classes + ".suffix", suffix);
            yaml.set("classes." + classes + ".damage", Double.valueOf(damage));
            yaml.set("classes." + classes + ".defense", Double.valueOf(defense));
            yaml.set("classes." + classes + ".speed", Double.valueOf(speed));
            yaml.set("classes." + classes + ".health", Integer.valueOf(health));
            yaml.set("classes." + classes + ".icon", icon);
            yaml.set("classes." + classes + ".slot", Integer.valueOf(slot));
            yaml.set("classes." + classes + ".ability", ability);
        }
    }

    public static class players
    {
        public static File file()
        {
            return new File(Bukkit.getPluginManager().getPlugin("RaioClasses").getDataFolder(), "players.yml");
        }

        public static YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file());

        public static void load()
        {
            YamlConfiguration.loadConfiguration(file());
        }

        public static void save()
        {
            try
            {
                yaml.save(file());
            }
            catch (IOException localIOException) {}
        }
    }

    public static class messages
    {
        public static File file()
        {
            return new File(Bukkit.getPluginManager().getPlugin("RaioClasses").getDataFolder(), "players.yml");
        }

        public static YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file());

        public static void load()
        {
            YamlConfiguration.loadConfiguration(file());
        }

        public static void save()
        {
            try
            {
                yaml.save(file());
            }
            catch (IOException localIOException) {}
        }
    }
}
