package com.somrpg.swordofmagic7.Core.DataBase;

import com.somrpg.swordofmagic7.Core.Map.MapData;
import com.somrpg.swordofmagic7.Core.Map.WarpGate.WarpGateData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

import static com.somrpg.swordofmagic7.Core.Generic.GenericConfig.DataBasePath;

public interface WarpGateLoader extends DataBase {
    static void load() {
        for (File file : DataBase.dump(new File(DataBasePath, "WarpGateData/"))) {
            try {
                FileConfiguration data = YamlConfiguration.loadConfiguration(file);
                String fileName = file.getName().replace(".yml", "");
                World world = Bukkit.getWorld(data.getString("Location.w", "world"));
                double x = data.getDouble("Location.x");
                double y = data.getDouble("Location.y");
                double z = data.getDouble("Location.z");
                float yaw = (float) data.getDouble("Location.yaw");
                float pitch = (float) data.getDouble("Location.pitch");
                Location loc = new Location(world, x, y, z, yaw, pitch);
                MapData nextMap = MapDataList.get(data.getString("NextMap"));
                String trigger = data.getString("Trigger");
                WarpGateData warp;
                if (data.isSet("Target")) {
                    String target = data.getString("Target");
                    warp = new WarpGateData(fileName, loc, target, nextMap, trigger);
                } else {
                    double xT = data.getDouble("TargetLocation.x");
                    double yT = data.getDouble("TargetLocation.y");
                    double zT = data.getDouble("TargetLocation.z");
                    float yawT = (float) data.getDouble("TargetLocation.yaw");
                    float pitchT = (float) data.getDouble("TargetLocation.pitch");
                    Location target = new Location(world, xT, yT, zT, yawT, pitchT);
                    warp = new WarpGateData(fileName, loc, target, nextMap, trigger);
                }

                if (data.getBoolean("Default", true)) {
                    warp.active();
                } else {
                    warp.disable();
                }
                warp.start();
                WarpGateList.put(fileName, warp);
            } catch (Exception e) {
                DataBase.loadError(file);
            }
        }
    }
}
