package xyz.trinets.minecraft.triminenet;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChannelSelector implements Listener {
    private TriMineNet plugin = TriMineNet.getPlugin(TriMineNet.class);

    @EventHandler
    public void onPlayerOpenChannelList (InventoryOpenEvent event) {
        InventoryView inventory = event.getView();
        String invName = inventory.getTitle();

        if (!invName.startsWith("TriMineNet::ChannelList:")) return;
        String letter = invName.split(":")[3];

        ItemStack spawnProtChannel = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta spawnProtMeta = spawnProtChannel.getItemMeta();
        spawnProtMeta.setDisplayName(ChatColor.RED + letter + "1 Channel: 접근 불가");
        List<String> spawnProtLore = Collections.singletonList(ChatColor.RED + "스폰 보호를 위해 사용 할 수 없습니다");
        spawnProtMeta.setLore(spawnProtLore);
        spawnProtChannel.setItemMeta(spawnProtMeta);

        inventory.setItem(0, spawnProtChannel);
        for (int a = 1; a < 27; a++) {
            int b = a + 1;

            ItemStack normalChannel = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
            ItemMeta normalChannelMeta = normalChannel.getItemMeta();
            normalChannelMeta.setDisplayName(ChatColor.GREEN + letter + b + " Channel 이동");
            String channelLocation;
            switch (letter) {
                case "N": {
                    channelLocation = ChatColor.RED + "0 " + ChatColor.GREEN + "10 " + ChatColor.BLUE + (b * -100);
                    break;
                }

                case "S": {
                    channelLocation = ChatColor.RED + "0 " + ChatColor.GREEN + "10 " + ChatColor.BLUE + (b * 100);
                    break;
                }

                case "W": {
                    channelLocation = ChatColor.RED + Integer.toString(b * -100) + " " + ChatColor.GREEN + "10 " + ChatColor.BLUE + "0";
                    break;
                }

                case "E": {
                    channelLocation = ChatColor.RED + Integer.toString(b * 100) + " " + ChatColor.GREEN + "10 " + ChatColor.BLUE + "0";
                    break;
                }

                default: {
                    channelLocation = ChatColor.RED + "? " + ChatColor.GREEN + "? " + ChatColor.BLUE + "?";
                    break;
                }
            }

            List<String> normalChannelLore = Collections.singletonList(channelLocation);
            normalChannelMeta.setLore(normalChannelLore);
            normalChannel.setItemMeta(normalChannelMeta);
            inventory.setItem(a, normalChannel);
        }
    }

    @EventHandler
    public void onPlayerChannelSelect (InventoryClickEvent event) {
        InventoryView inventory = event.getView();
        HumanEntity player = event.getWhoClicked();
        World world = player.getWorld();
        String invName = inventory.getTitle();
        int selected = event.getSlot();

        if (!invName.startsWith("TriMineNet::ChannelList:")) return;
        String letter = invName.split(":")[3];

        event.setCancelled(true);
        if (selected < 1) return;
        selected++;

        Location location;
        switch (letter) {
            case "N": {
                location = new Location(world, 0.5, 10, selected * -100 + 0.5);
                break;
            }

            case "S": {
                location = new Location(world, 0.5, 10, selected * 100 + 0.5);
                break;
            }

            case "W": {
                location = new Location(world, selected * -100 + 0.5, 10, 0.5);
                break;
            }

            case "E": {
                location = new Location(world, selected * 100 + 0.5, 10, 0.5);
                break;
            }

            default: {
                location = null;
                break;
            }
        }

        if (location == null) {
            player.sendMessage("Teleport Location is not Correct!");
        } else {
            player.teleport(location);
            player.sendMessage(letter + selected + " Channel");
        }
    }
}
