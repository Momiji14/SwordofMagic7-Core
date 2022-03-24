package com.somrpg.swordofmagic7.Core.RayTrace;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

public class RayDataContainer implements RayData {

    private final Entity hitEntity;
    private final Block hitBlock;
    private final Location hitLocation;

    RayDataContainer(Entity entity, Block block, Location location) {
        hitEntity = entity;
        hitBlock = block;
        hitLocation = location;
    }
    @Override
    public Entity getHitEntity() {
        return hitEntity;
    }
    @Override
    public Block getHitBlock() {
        return hitBlock;
    }
    @Override
    public Location getHitLocation() {
        return hitLocation;
    }
}
