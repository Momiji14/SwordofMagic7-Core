package com.somrpg.swordofmagic7.Core.DataBase;

import com.somrpg.swordofmagic7.Core.Map.MapData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

import static com.somrpg.swordofmagic7.Core.Generic.GenericConfig.DataBasePath;

public class MapDataLoader implements SomLoader {
    public static void load() {
        for (File file : DataLoader.dump(new File(DataBasePath, "MapData"))) {
            try {
                String fileName = file.getName().replace(".yml", "");
                FileConfiguration data = YamlConfiguration.loadConfiguration(file);
                String display = data.getString("Display");
                String color = data.getString("Color");
                int level = data.getInt("Level");
                boolean safe = data.getBoolean("Safe");
                MapData mapData = new MapData(fileName, display, color, level, safe);
                if (data.isSet("Life.Mine")) {
                    for (String str : data.getStringList("Life.Mine")) {
                        String[] split = str.split(",");
                        mapData.getGatheringData().put(split[0], split[1]);
                    }
                }
                if (data.isSet("Life.Lumber")) {
                    for (String str : data.getStringList("Life.Lumber")) {
                        String[] split = str.split(",");
                        mapData.getGatheringData().put(split[0], split[1]);
                    }
                }
                if (data.isSet("Life.Harvest")) {
                    for (String str : data.getStringList("Life.Harvest")) {
                        String[] split = str.split(",");
                        mapData.getGatheringData().put(split[0], split[1]);
                    }
                }
                if (data.isSet("Life.Angler")) {
                    int i = 0;
                    for (String str : data.getStringList("Life.Angler")) {
                        mapData.getGatheringData().put("Fishing-" + i, str);
                        i++;
                    }
                }
                MapDataList.put(fileName, mapData);
            } catch (Exception e) {
                e.printStackTrace();
                DataLoader.loadError(file);
            }
        }
    }
}
