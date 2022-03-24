package com.somrpg.swordofmagic7.Core.Map.WarpGate;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.somrpg.swordofmagic7.Core.DataBase.DataBase;
import com.somrpg.swordofmagic7.Core.DataBase.WarpGateLoader;
import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Map.MapData;
import com.somrpg.swordofmagic7.Core.Particle.ParticleData;
import com.somrpg.swordofmagic7.Core.SomCore;
import com.somrpg.swordofmagic7.Core.SomHologram;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Map;

public interface WarpGate {

    void active();
    void disable();
    void activeAtTime(int time);

    void usePlayer(@NonNull Player player);

    String getId();
    Location getLocation();
    Location getTargetLocation();
    String getTarget();
    String getTrigger();
    MapData getNextMap();

    static void Selector(@NonNull Player player) {
        Location pLoc = player.getLocation();
        for (Map.Entry<String, WarpGateData> entry : WarpGateLoader.WarpGateList.entrySet()) {
            if (entry.getValue().getLocation().distance(pLoc) < 1.5) {
                if (WarpGateLoader.WarpGateList.containsKey(entry.getValue().getTarget()) || entry.getValue().getTargetLocation() != null) {
                    entry.getValue().usePlayer(player);
                } else {
                    SomCore.log("§cError NotFundWarpGate: " + entry.getValue().getTarget() + " at " + entry.getKey());
                }
            }
        }
    }

    default void start() {
        Hologram hologram = SomHologram.createHologram("WarpGateHologram:" + getId(), getLocation().clone().add(0, 4, 0));
        hologram.appendTextLine(getNextMap().getColor() + "§l《" + getNextMap().getDisplay() + "》");
        hologram.appendTextLine("");
        hologram.appendTextLine(getNextMap().getColor() + "§l推奨Lv" + getNextMap().getLevel());
        ParticleData particleData = new ParticleData(Particle.SPELL_WITCH).setSpeed(0.1f).setVector(GenericConfig.VectorUp);
        World world = getLocation().getWorld();
        new BukkitRunnable() {
            int i = 0;
            final double increment = (2 * Math.PI) / 90;
            final double radius = 1.5;
            @Override
            public void run() {
                double angle = i * increment;
                double x = radius * Math.cos(angle);
                double z = radius * Math.sin(angle);
                Location nLoc = new Location(world, getLocation().getX() + x, getLocation().getY(), getLocation().getZ() + z);
                Location nLoc2 = new Location(world, getLocation().getX() - x, getLocation().getY(), getLocation().getZ() - z);
                particleData.spawn(nLoc);
                particleData.spawn(nLoc2);
                i++;
            }
        }.runTaskTimerAsynchronously(SomCore.getPlugin(), 0, 1);
    }
}
