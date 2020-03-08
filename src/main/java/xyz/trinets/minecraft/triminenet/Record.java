package xyz.trinets.minecraft.triminenet;

import org.bukkit.Location;

public class Record {
    private String uid;
    private Location location;

    public boolean is (String tuid) {
        return uid.equals(tuid);
    }

    public Location getLocation () {
        return location;
    }

    public void regist (String uid, Location location) {
        this.uid = uid;
        this.location = location;
    }
}
