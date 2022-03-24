package com.somrpg.swordofmagic7.Core.Entity.Eemey;

import org.bukkit.Location;

import java.io.File;
import java.util.List;

public interface SomSpawner {

    static SomSpawner create(EnemyData enemyData, Location location, int level, int maxMob, int radius, int radiusY, int perSpawn, File file) {
        return new SomSpawnerContainer(enemyData, location, level, maxMob, radius, radiusY, perSpawn, file);
    }

    void start();

    EnemyData getEnemyData();

    Location getLocation();

    int getLevel();

    int getMaxMob();

    int getRadius();

    int getRadiusY();

    int getPerSpawn();

    File getFile();

    boolean isStarted();

    List<EnemyController> getSpawnedEnemy();
}
