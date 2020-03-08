package xyz.trinets.minecraft.triminenet;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class InitEvents implements Listener {
    private TriMineNet plugin = TriMineNet.getPlugin(TriMineNet.class);
    private Recorder recorder = plugin.recorder;
    private World world = plugin.mainWorld;
    private Location spawn = new Location(world, 0.5, 5, 0.5);

    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setGameMode(GameMode.CREATIVE);
        player.teleport(spawn);

        player.sendActionBar(
                ChatColor.BOLD + "" +
                ChatColor.AQUA + "Tri" +
                ChatColor.WHITE + "MineNet " +
                ChatColor.GOLD + "V3" +
                ChatColor.RESET
        );
    }

    @EventHandler
    public void onPlayerMove (PlayerMoveEvent event) {
        Player player = event.getPlayer();
        String uid = player.getUniqueId().toString();
        Location to = event.getTo();

        if (to.getBlockY() < 0) {
            Location target = recorder.getLastBlock(uid);

            target.setPitch(to.getPitch());
            target.setYaw(to.getYaw());
            player.teleport(target);

            player.sendMessage(
                    ChatColor.BOLD + "" +
                    ChatColor.RED + "어이쿠 발이 미끄러졌네" +
                    ChatColor.RESET + " | " +
                    ChatColor.WHITE + "최근 위치(" +
                    ChatColor.RED + Math.round(target.getX() * 100.0) / 100.0 +
                    ChatColor.WHITE + "," +
                    ChatColor.GREEN + Math.round(target.getY() * 100.0) / 100.0 +
                    ChatColor.WHITE + "," +
                    ChatColor.BLUE + Math.round(target.getZ() * 100.0) / 100.0 +
                    ChatColor.WHITE + ")로 이동됨"
            );
        }

        Location bottomBlock = to.clone();
        bottomBlock.setY(bottomBlock.getBlockY() - 1);
        Block block = bottomBlock.getBlock();
        Material material = block.getType();

        if (!material.isAir()) recorder.setLastBlock(uid, to);
    }
}
