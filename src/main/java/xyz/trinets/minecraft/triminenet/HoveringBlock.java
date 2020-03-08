package xyz.trinets.minecraft.triminenet;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class HoveringBlock implements Listener {
    @EventHandler
    public void onPlayerInteract (PlayerInteractEvent event) {
        Action action = event.getAction();
        if (action != Action.RIGHT_CLICK_AIR) return;

        Player player = event.getPlayer();
        Location location = player.getLocation();
        Material material = player.getInventory().getItemInMainHand().getType();
        location.setY(location.getY() + 1);
        Material target = location.getBlock().getType();

        if (!material.isBlock() || !target.isAir()) return;
        boolean isInSpawnX = location.getBlockX() < 100 && location.getBlockX() > -100;
        boolean isInSpawnZ = location.getBlockZ() < 100 && location.getBlockZ() > -100;

        if (isInSpawnX && isInSpawnZ) {
            player.sendActionBar(
                    ChatColor.BOLD + "스폰 보호를 위해 스폰과 " +
                            ChatColor.RED + "X" + ChatColor.WHITE + ", " +
                            ChatColor.BLUE + "Z" + ChatColor.WHITE + " 100블럭 이내 " +
                            ChatColor.UNDERLINE + "블럭을 설치하거나 부술수 없습니다"
            );
        } else {
            location.getBlock().setType(material);
        }
        event.setCancelled(true);
    }
}
