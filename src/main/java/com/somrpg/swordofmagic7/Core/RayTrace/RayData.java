package com.somrpg.swordofmagic7.Core.RayTrace;

import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public interface RayData {
    Entity getHitEntity();
    Block getHitBlock();
    Location getHitLocation();

    static RayData rayTrace(Location location, Vector direction, double length) {
        RayTraceResult result = location.getWorld().rayTraceBlocks(location, direction, length, FluidCollisionMode.NEVER, true);
        return new RayDataContainer(result.getHitEntity(), result.getHitBlock(), result.getHitPosition().toLocation(location.getWorld()));
    }
}
