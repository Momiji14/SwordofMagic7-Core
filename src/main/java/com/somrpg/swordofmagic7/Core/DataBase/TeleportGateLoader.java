package com.somrpg.swordofmagic7.Core.DataBase;

import com.somrpg.swordofmagic7.Core.Map.MapData;
import com.somrpg.swordofmagic7.Core.Map.TeleportGate.TeleportGateData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.somrpg.swordofmagic7.Core.DataBase.DataLoader.dump;
import static com.somrpg.swordofmagic7.Core.DataBase.DataLoader.loadError;
import static com.somrpg.swordofmagic7.Core.Generic.GenericConfig.DataBasePath;

public interface TeleportGateLoader extends SomLoader {

    Map<Integer, String> TeleportGateMenu = new HashMap<>();

    static void load() {
        for (File file : dump(new File(DataBasePath, "TeleportGateData/"))) {
            try {
                FileConfiguration data = YamlConfiguration.loadConfiguration(file);
                String fileName = file.getName().replace(".yml", "");
                if (!fileName.equalsIgnoreCase("GUI")) {
                    World world = Bukkit.getWorld(data.getString("Location.w", "world"));
                    double x = data.getDouble("Location.x");
                    double y = data.getDouble("Location.y");
                    double z = data.getDouble("Location.z");
                    float yaw = (float) data.getDouble("Location.yaw");
                    float pitch = (float) data.getDouble("Location.pitch");
                    Location loc = new Location(world, x, y, z, yaw, pitch);
                    String display = data.getString("Display");
                    Material material = Material.getMaterial(data.getString("Icon"));
                    String title = data.getString("Title");
                    String subtitle = data.getString("Subtitle");
                    boolean defaultActive = data.getBoolean("DefaultActive");
                    MapData mapData = MapDataList.get(data.getString("Map"));
                    TeleportGateData teleportGate = new TeleportGateData(fileName, display, material, title, subtitle, loc, mapData, defaultActive);
                    TeleportGateList.put(fileName, teleportGate);
                    teleportGate.start();
                } else {
                    for (int i = 0; i < 54; i++) {
                        if (data.isSet("TeleportGateMenu." + i)) {
                            TeleportGateMenu.put(i, data.getString("TeleportGateMenu." + i));
                        }
                    }
                }
            } catch (Exception e) {
                loadError(file);
            }
        }
    }
}
