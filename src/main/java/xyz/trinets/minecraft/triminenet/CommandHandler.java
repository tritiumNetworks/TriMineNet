package xyz.trinets.minecraft.triminenet;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {
    private TriMineNet plugin = TriMineNet.getPlugin(TriMineNet.class);

    @Override
    public boolean onCommand (CommandSender sender, Command command, String label, String[] arg) {
        String commandName = command.getName();
        boolean isNomalPlayer = sender instanceof Player;
        if (!isNomalPlayer) return true;

        Player player = (Player) sender;
        Location pLocation = player.getLocation();

        switch (commandName) {
            case "spawn": {
                Location location = new Location(plugin.mainWorld, 0.5, 5, 0.5, pLocation.getYaw(), pLocation.getPitch());
                player.teleport(location);
                break;
            }
        }
        return true;
    }
}
