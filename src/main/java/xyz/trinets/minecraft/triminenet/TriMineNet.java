package xyz.trinets.minecraft.triminenet;

import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public final class TriMineNet extends JavaPlugin {
    public World mainWorld;
    public Recorder recorder = new Recorder();

    @Override
    public void onEnable() {
        getMainWorld(getServer());
        getServer().getPluginManager().registerEvents(new InitEvents(), this);
        getServer().getPluginManager().registerEvents(new SpawnProtection(), this);
        getServer().getPluginManager().registerEvents(new TNTProtection(), this);
        getServer().getPluginManager().registerEvents(new HoveringBlock(), this);
        getServer().getPluginManager().registerEvents(new ChannelSelector(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void getMainWorld (Server server) {
        server.getWorlds().forEach((w) -> {
            if (w.getEnvironment() == World.Environment.NORMAL) mainWorld = w;
        });
    }
}
