package xyz.trinets.minecraft.triminenet;

import org.bukkit.Location;

import java.util.ArrayList;

public class Recorder {
    private ArrayList<Record> lastBlockRecord = new ArrayList<Record>();

    public Location getLastBlock (String uid) {
        Record found = null;
        for (Record record : lastBlockRecord) {
            if (record.is(uid)) found = record;
        }
        return found.getLocation();
    }

    public void setLastBlock (String uid, Location location) {
        Record record = new Record();
        record.regist(uid, location);
        lastBlockRecord.add(record);
    }
}
