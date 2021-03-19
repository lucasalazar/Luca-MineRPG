package me.raioclasses.util.abilities;

import me.raioclasses.type.Scan;
import me.raioclasses.util.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FastMiner
        implements Listener {
    public static boolean check(Player player) {
        return Scan.classes.getAbility(player).contains("FAST_MINER");
    }
    
    @EventHandler
    public void onMine(BlockDamageEvent e) {
        final Player player = e.getPlayer();
        if (Config.players.yaml.contains(player.getUniqueId().toString())) {
            if (check(player)) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20, 0));
                }
            }
        }
    }