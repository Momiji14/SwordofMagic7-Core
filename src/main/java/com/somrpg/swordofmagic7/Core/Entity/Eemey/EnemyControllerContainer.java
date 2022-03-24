package com.somrpg.swordofmagic7.Core.Entity.Eemey;

import com.somrpg.swordofmagic7.Core.Entity.BaseEntityContainer;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.GenericStatus;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.GenericStatusContainer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.UUID;

public class EnemyControllerContainer implements EnemyController {

    private final Entity entity;
    private final EnemyData enemyData;
    private final Location spawnLocation;
    private BaseEntityContainer baseEntityContainer;

    EnemyControllerContainer(EnemyData enemyData, Location spawnLocation, int level) {
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
    }

    public String getDecoDisplay() {
        return "§c§l《" + enemyData.getDisplay() + " Lv" + getLevel() + "》";
    }

    public void statusUpdate() {
        double multiply = EnemyController.StatusMultiply(getLevel());
        GenericStatus status = enemyData.getStatus().multiplyStatusParameter(new GenericStatusContainer().setAllStatusParameter(multiply));
        setMaxHealth(enemyData.getStatus().getMaxHealth() * multiply);
    }

    @Override
    public BaseEntityContainer getBaseEntityContainer() {
        return baseEntityContainer;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public void setOverrideTargetLocation(Location overrideTargetLocation) {
        this.overrideTargetLocation = overrideTargetLocation;
    }

    private Entity target;
    private Location overrideTargetLocation;
}
