package com.somrpg.swordofmagic7.Core.DataBase;

import com.somrpg.swordofmagic7.Core.Entity.Eemey.EnemyData;
import com.somrpg.swordofmagic7.Core.Entity.Eemey.SomSpawner;
import com.somrpg.swordofmagic7.Core.Entity.Eemey.SomSpawnerContainer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.somrpg.swordofmagic7.Core.Generic.GenericConfig.DataBasePath;

public interface SomSpawnerLoader {

    Map<String, SomSpawner> SpawnerDataList = new HashMap<>();

    static void load() {
        for (File file : DataBase.dump(new File(DataBasePath, "MobSpawner/"))) {
            try {
                String fileName = file.getName().replace(".yml", "");
                FileConfiguration data = YamlConfiguration.loadConfiguration(file);
                EnemyData enemyData = EnemyDataLoader.getEnemyData(data.getString("MobData"));
                int level = data.getInt("Level");
                int radius = data.getInt("Radius");
                int radiusY = data.getInt("RadiusY");
                int maxMob = data.getInt("MaxMob");
                int perSpawn = data.getInt("PerSpawn");
                double x = data.getDouble("Location.x");
                double y = data.getDouble("Location.y");
                double z = data.getDouble("Location.z");
                Location location = new Location(Bukkit.getWorld(data.getString("Location.w", "world")), x, y, z);
                SomSpawner spawner = new SomSpawnerContainer(enemyData, location, level, maxMob, radius, radiusY, perSpawn, file);
                SpawnerDataList.put(fileName, spawner);
            } catch (Exception e) {
                DataBase.loadError(file);
            }
        }
    }

}
