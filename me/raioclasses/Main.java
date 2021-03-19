package me.raioclasses;

import me.raioclasses.events.JoinAndQuit;
import me.raioclasses.events.NewPlayer;
import me.raioclasses.events.PrefixAndSuffix;
import me.raioclasses.util.Config;
import me.raioclasses.util.GUI;
import me.raioclasses.util.abilities.*;
import me.raioclasses.util.stats.Damage;
import me.raioclasses.util.stats.Defense;
import me.raioclasses.util.stats.Speed;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§ePlugin iniciado com sucesso");
        Bukkit.getConsoleSender().sendMessage("§ePlugin desenvolvido pelo Luca para a Rede Raio.");
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new NewPlayer(), this);
        pm.registerEvents(new JoinAndQuit(), this);
        pm.registerEvents(new PrefixAndSuffix(), this);
        pm.registerEvents(new GUI(), this);
        pm.registerEvents(new InvisibleSneak(), this);
        pm.registerEvents(new AttackBlock(), this);
        pm.registerEvents(new FireHand(), this);
        pm.registerEvents(new LifeSteal(), this);
        pm.registerEvents(new DeathExplosion(), this);
        pm.registerEvents(new FastMiner(), this);
        pm.registerEvents(new Damage(), this);
        pm.registerEvents(new Defense(), this);
        pm.registerEvents(new Speed(), this);
        pm.registerEvents(new RepairHand(), this);
        pm.registerEvents(new PoisonHand(), this);
        pm.registerEvents(new MagicianHand(), this);

        Config.setting.create();
        Config.classes.create();
    }

    public static HashMap<Player, Integer> cooldown = new HashMap();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if ((sender instanceof Player))
        {
            Player player = (Player)sender;
            if (label.equalsIgnoreCase("classe")) {
                if (!(Config.players.yaml.contains(player.getUniqueId().toString())))
                {
                    if (!cooldown.containsKey(player))
                    {
                        GUI.open(player);
                    }
                    else
                    {
                        int days = ((Integer)cooldown.get(player)).intValue() / 86400;
                        int hours = ((Integer)cooldown.get(player)).intValue() % 86400 / 3600;
                        int minutes = ((Integer)cooldown.get(player)).intValue() % 86400 % 3600 / 60;
                        int seconds = ((Integer)cooldown.get(player)).intValue() % 86400 % 3600 % 60;

                        sender.sendMessage(ChatColor.RED + "Esse comando está em cooldown por " + days + " dias " + hours + " horas " + minutes + " minutos " + seconds + " segundos.");
                    }
                }
                else {
                    player.sendMessage(ChatColor.RED + "Você já escolheu uma classe e não poderá mais mudar de escolha.");
                }
            }
        }
        return false;
    }
}
