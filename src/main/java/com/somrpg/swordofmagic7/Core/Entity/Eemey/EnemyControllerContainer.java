package com.somrpg.swordofmagic7.Core.Entity.Eemey;

import com.somrpg.swordofmagic7.Core.Entity.BaseEntityContainer;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.GenericStatus;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.GenericStatusContainer;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class EnemyControllerContainer implements EnemyController {

    private final Entity entity;
    private final EnemyData enemyData;
    private final Location spawnLocation;
    private final BaseEntityContainer baseEntityContainer;

    EnemyControllerContainer(EnemyData enemyData, Location spawnLocation, int level) {
        baseEntityContainer = new BaseEntityContainer();
        this.enemyData = enemyData;
        this.spawnLocation = spawnLocation.clone();
        entity = spawnLocation.getWorld().spawnEntity(spawnLocation, enemyData.getEntityType(), false);
        entity.setCustomNameVisible(true);
        entity.setCustomName(getDecoDisplay());

        if (enemyData.getDisguise() != null) {
            enemyData.getDisguise().setEntity(entity);
            enemyData.getDisguise().setDisguiseName(getDecoDisplay());
            enemyData.getDisguise().startDisguise();
        }

        setLevel(level);
    }

    public String getDecoDisplay() {
        return "§c§l《" + enemyData.getDisplay() + " Lv" + getLevel() + "》";
    }

    @Override
    public void statusUpdate() {
        double multiply = EnemyController.StatusMultiply(getLevel());
        GenericStatus status = enemyData.getStatus().multiplyStatusParameter(new GenericStatusContainer().setAllStatusParameter(multiply));
        setMaxHealth(enemyData.getStatus().getMaxHealth() * multiply);
    }

    @Override
    public BaseEntityContainer getBaseEntityContainer() {
        return baseEntityContainer;
    }

    @Override
    public Location getSpawnLocation() {
        return spawnLocation;
    }

    @Override
    public void setOverrideTargetLocation(Location overrideTargetLocation) {
        this.overrideTargetLocation = overrideTargetLocation;
    }

    @Override
    public Entity getEntity() {
        return entity;
    }

    @Override
    public boolean isDead() {
        return getEntity().isDead();
    }

    private Entity target;
    private Location overrideTargetLocation;
}
