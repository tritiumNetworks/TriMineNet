package xyz.trinets.minecraft.triminenet;

import com.destroystokyo.paper.Title;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;
import java.util.List;

public class TNTProtection implements Listener {
    private Material[] dangerItemArr = { Material.TNT, Material.END_CRYSTAL, Material.TNT_MINECART, Material.CREEPER_SPAWN_EGG };
    private List<Material> dangerItem = Arrays.asList(dangerItemArr);

    @EventHandler
    public void onPlayerGetTNT (InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Material material = event.getCursor().getType();

        if (!dangerItem.contains(material)) return;

        event.setCancelled(true);
        player.closeInventory();
        player.sendTitle(new Title(
            ChatColor.BOLD + "" +
            ChatColor.RED + "저런!",
            ChatColor.BOLD + "" +
            ChatColor.GOLD + "당신의 손은 그 위험물을 들기에 충분히 단단하지 않습니다",
            0, 20, 10
        ));
    }
}
