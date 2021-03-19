package me.raioclasses.util.abilities;

import me.raioclasses.type.Scan;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class RepairHand implements Listener {
    @EventHandler
    public void onAnvil(InventoryOpenEvent e){
        if(e.getInventory().getType() == InventoryType.ANVIL){
            Player player = (Player)e.getPlayer();
            if(!(Scan.classes.getAbility(player).contains("REPAIR_HAND"))){
                e.setCancelled(true);
                player.sendMessage(ChatColor.RED + "Você precisa ser da classe " + ChatColor.WHITE + "ferreiro" + ChatColor.RED + " para poder abrir a bigorna.");
            }else{
                e.setCancelled(false);
                player.sendMessage(ChatColor.YELLOW + "Como você é um " + ChatColor.WHITE + "ferreiro" + ChatColor.YELLOW + ", a bigorna foi aberta.");
            }
        }
    }
}
