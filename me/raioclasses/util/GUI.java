package me.raioclasses.util;

import java.util.ArrayList;
import java.util.HashMap;

import me.raioclasses.Main;
import me.raioclasses.type.Scan;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class GUI
        implements Listener
{
    static Inventory gui = Bukkit.createInventory(null, 18, "Classes");

    public static void open(Player player)
    {
        for (String classes : Config.classes.yaml.getConfigurationSection("classes").getKeys(false))
        {
            ItemStack item = new ItemStack(Material.valueOf(Config.classes.yaml.getString("classes." + classes + ".icon")));
            ItemMeta itemMeta = item.getItemMeta();
            ArrayList<String> lore = new ArrayList();
            itemMeta.setDisplayName(ChatColor.RESET + classes);
            lore.add(" ");
            lore.add(ChatColor.YELLOW + "Vida : " + ChatColor.GRAY + Config.classes.yaml.getString(new StringBuilder().append("classes.").append(classes).append(".health").toString()));
            lore.add(ChatColor.YELLOW + "Dano : " + ChatColor.GRAY + Config.classes.yaml.getString(new StringBuilder().append("classes.").append(classes).append(".damage").toString()));
            lore.add(ChatColor.YELLOW + "Defesa : " + ChatColor.GRAY + Config.classes.yaml.getString(new StringBuilder().append("classes.").append(classes).append(".defense").toString()));
            lore.add(ChatColor.YELLOW + "Velocidade : " + ChatColor.GRAY + Config.classes.yaml.getString(new StringBuilder().append("classes.").append(classes).append(".speed").toString()));
            if (Config.classes.yaml.getString("classes." + classes + ".ability") != null) {
                lore.add(ChatColor.YELLOW + "Habilidade : " + ChatColor.GRAY + Config.classes.yaml.getString(new StringBuilder().append("classes.").append(classes).append(".ability").toString())
                        .replaceAll("ATTACK_BLOCK", "Bloqueio de ataques")
                        .replaceAll("INVISIBLE_SNEAK", "Invisibilidade ao agachar")
                        .replaceAll("FIRE_HAND", "Mão de fogo")
                        .replaceAll("LIFE_STEAL", "Roubo de vida")
                        .replaceAll("DEATH_EXPLOSION", "Morte explosiva")
                        .replaceAll("FAST_MINER", "Minerador experiente")
                        .replaceAll("POISON_HAND", "Mão venenosa")
                        .replaceAll("REPAIR_HAND", "Reparação divina")
                        .replaceAll("MAGICIAN_HAND", "Alquimia")
                        .replaceAll("\\[", "")
                        .replaceAll("]", ""));
            }
            lore.add(" ");
            lore.add(ChatColor.GRAY + "Clique para selecionar essa classe.");
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);

            gui.setItem(Config.classes.yaml.getInt("classes." + classes + ".slot"), item);

            player.openInventory(gui);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        if ((e.getWhoClicked() instanceof Player))
        {
            final Player player = (Player)e.getWhoClicked();
            int slot = e.getSlot();
            Inventory inv = e.getInventory();
            if (inv.getName().equals("Classes"))
            {
                for (String classes : Config.classes.yaml.getConfigurationSection("classes").getKeys(false)) {
                    if (slot == Config.classes.yaml.getInt("classes." + classes + ".slot"))
                    {
                        String selected = classes;
                        if (player.hasPermission("class." + selected))
                        {
                            Scan.classes.set(player, selected);
                            float speed = Scan.classes.getSpeed(player);
                            player.setWalkSpeed(0.01F * speed);
                            player.sendMessage(ChatColor.YELLOW + "Você selecionou a classe " + ChatColor.WHITE + selected + ChatColor.YELLOW + ". Você não poderá mais trocar de classe até o final do jogo." );
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 10.0F, 10.0F);
                            player.setMaxHealth(Scan.classes.getMaxHealth(player));
                            player.setHealth(Scan.classes.getMaxHealth(player));
                            Main.cooldown.put(player, Integer.valueOf(Config.setting.yaml.getInt("class_command_cooldown")));

                            new BukkitRunnable()
                            {
                                public void run()
                                {
                                    if (((Integer)Main.cooldown.get(player)).intValue() > 0) {
                                        Main.cooldown.put(player, Integer.valueOf(((Integer)Main.cooldown.get(player)).intValue() - 1));
                                    }
                                    if (((Integer)Main.cooldown.get(player)).intValue() == 0)
                                    {
                                        Main.cooldown.remove(player);

                                        cancel();
                                    }
                                }
                            }.runTaskTimer(JavaPlugin.getPlugin(Main.class), 20L, 20L);
                        }
                        else
                        {
                            player.sendMessage(ChatColor.RED + "Você precisa ser um " + ChatColor.YELLOW + "VIP" + ChatColor.RED + " para escolher essa classe.");
                        }
                    }
                }
                e.setCancelled(true);
            }
        }
    }
}
