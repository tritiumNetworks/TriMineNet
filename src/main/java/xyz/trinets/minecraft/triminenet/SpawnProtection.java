package xyz.trinets.minecraft.triminenet;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class SpawnProtection implements Listener {
    @EventHandler
    public void onPlayerBreak (BlockBreakEvent event) {
        Player player = event.getPlayer();
        Location location = event.getBlock().getLocation();

        boolean isInSpawnX = location.getBlockX() < 100 && location.getBlockX() > -100;
        boolean isInSpawnZ = location.getBlockZ() < 100 && location.getBlockZ() > -100;

        if (isInSpawnX && isInSpawnZ) {
            player.sendActionBar(
                ChatColor.BOLD + "스폰 보호를 위해 스폰과 " +
                ChatColor.RED + "X" + ChatColor.WHITE + ", " +
                ChatColor.BLUE + "Z" + ChatColor.WHITE + " 100블럭 이내 " +
                ChatColor.UNDERLINE + "블럭을 설치하거나 부술수 없습니다"
            );
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerPlace (BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Location location = event.getBlock().getLocation();

        boolean isInSpawnX = location.getBlockX() < 100 && location.getBlockX() > -100;
        boolean isInSpawnZ = location.getBlockZ() < 100 && location.getBlockZ() > -100;

        if (isInSpawnX && isInSpawnZ) {
            player.sendActionBar(
                ChatColor.BOLD + "스폰 보호를 위해 스폰과 " +
                ChatColor.RED + "X" + ChatColor.WHITE + ", " +
                ChatColor.BLUE + "Z" + ChatColor.WHITE + " 100블럭 이내 " +
                ChatColor.UNDERLINE + "블럭을 설치하거나 부술수 없습니다"
            );
            event.setCancelled(true);
        }
    }
}
