package com.somrpg.swordofmagic7.Core.Entity.Eemey;

import com.somrpg.swordofmagic7.Core.Entity.BaseEntity;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public interface EnemyController extends BaseEntity {

    static EnemyController spawn(EnemyData enemyData, Location spawnLocation, int level) {
        return new EnemyControllerContainer(enemyData, spawnLocation, level);
    }

    Map<Entity, EnemyController> EnemyList = new HashMap<>();
    static double StatusMultiply(int level) {
        return Math.pow(0.74+(level/3f), 1.4);
    }

    void statusUpdate();

    Location getSpawnLocation();

    void setOverrideTargetLocation(Location overrideTargetLocation);

    Entity getEntity();

    boolean isDead();
}
