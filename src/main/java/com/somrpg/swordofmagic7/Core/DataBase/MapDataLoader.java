package com.somrpg.swordofmagic7.Core.DataBase;

import com.somrpg.swordofmagic7.Core.Map.MapData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.somrpg.swordofmagic7.Core.Generic.GenericConfig.DataBasePath;

public interface MapDataLoader {

    Map<String, MapData> MapDataList = new HashMap<>();

    static void load() {
        for (File file : DataBase.dump(new File(DataBasePath, "MapData"))) {
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
                DataBase.loadError(file);
            }
        }
    }

    @Nullable
    static MapData getMapData(String key) {
        if (MapDataList.containsKey(key)) {
            return MapDataList.get(key);
        }
        return null;
    }

}
