package com.somrpg.swordofmagic7.Core.Entity.Eemey;

import com.somrpg.swordofmagic7.Core.Player.PlayerList;
import com.somrpg.swordofmagic7.Core.SomCore;
import org.bukkit.Location;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.somrpg.swordofmagic7.Core.Generic.GenericConfig.VectorDown;
import static com.somrpg.swordofmagic7.Core.Generic.GenericConfig.VectorUp;

public class SomSpawnerContainer implements SomSpawner {

    private final EnemyData enemyData;
    private final Location location;
    private final int level;
    private final int maxMob;
    private final int radius;
    private final int radiusY;
    private final int perSpawn;
    private final File file;

    private boolean isStarted = false;
    private final List<EnemyController> spawnedEnemy = new ArrayList<>();

    public SomSpawnerContainer(EnemyData enemyData, Location location, int level, int maxMob, int radius, int radiusY, int perSpawn, File file) {
        this.enemyData = enemyData;
        this.location = location;
        this.level = level;
        this.maxMob = maxMob;
        this.radius = radius;
        this.radiusY = radiusY;
        this.perSpawn = perSpawn;
        this.file = file;
    }

    @Override
    public void start() {
        if (!isStarted()) {
            isStarted = true;
            SomCore.getSomTask().AsyncTaskTimer(() -> {
                getSpawnedEnemy().removeIf(EnemyController::isDead);
                int perSpawn = getSpawnedEnemy().size() + getPerSpawn() > getMaxMob() ? 0 : getMaxMob() - getSpawnedEnemy().size();
                if (perSpawn > 0 && PlayerList.isInPlayer(getLocation(), getRadius() + 32)) {
                    for (int i = 0; i < perSpawn; i++) {
                        double x = getLocation().getX() + (2 * SomCore.getRandom().nextDouble() * getRadius()) - getRadius();
                        double z = getLocation().getZ() + (2 * SomCore.getRandom().nextDouble() * getRadius()) - getRadius();
                        double y = getLocation().getY() + getRadiusY();
                        Location origin = new Location(getLocation().getWorld(), x, y, z, 0,90);
                        for (int i2 = 0; i2 < getRadiusY()*2; i2++) {
                            if (!origin.getBlock().getType().isSolid() && origin.add(VectorDown).getBlock().getType().isSolid()) {
                                SomCore.getSomTask().SyncTask(() -> {
                                    getSpawnedEnemy().add(EnemyController.spawn(getEnemyData(), origin.add(VectorUp), getLevel()));
                                });
                            }
                        }
                    }
                }
            }, 30);
        }
    }

    @Override
    public EnemyData getEnemyData() {
        return enemyData;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getMaxMob() {
        return maxMob;
    }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public int getRadiusY() {
        return radiusY;
    }

    @Override
    public int getPerSpawn() {
        return perSpawn;
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public boolean isStarted() {
        return isStarted;
    }

    @Override
    public List<EnemyController> getSpawnedEnemy() {
        return spawnedEnemy;
    }
}
