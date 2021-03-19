package me.raioclasses.util.abilities;

import me.raioclasses.type.Scan;
import me.raioclasses.util.Config;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

public class MagicianHand implements Listener {
    @EventHandler
    public void onAlchemy(InventoryOpenEvent e) {
        Player player = (Player)e.getPlayer();
        if (e.getInventory().getType() == InventoryType.BREWING) {
            if (Config.players.yaml.contains(player.getUniqueId().toString())) {
                if (!(Scan.classes.getAbility(player).contains("MAGICIAN_HAND"))) {
                    e.setCancelled(true);
                    player.sendMessage(ChatColor.RED + "Você precisa ser da classe " + ChatColor.WHITE + "alquimista" + ChatColor.RED + " para abrir o suporte de poções");
                } else {
                    e.setCancelled(false);
                    player.sendMessage(ChatColor.YELLOW + "Como você é um " + ChatColor.WHITE + "alquimista" + ChatColor.YELLOW + ", o suporte de poções foi aberto.");
                }
            }else{
                e.setCancelled(true);
                player.sendMessage(ChatColor.RED + "Você precisa ser da classe " + ChatColor.WHITE + "alquimista" + ChatColor.RED + " para abrir o suporte de poções");
            }
        }
    }
}