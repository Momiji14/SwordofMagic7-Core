package com.somrpg.swordofmagic7.Core.Map.WarpGate;

import com.somrpg.swordofmagic7.Core.DataBase.WarpGateLoader;
import com.somrpg.swordofmagic7.Core.Generic.Particle.ParticleData;
import com.somrpg.swordofmagic7.Core.Map.MapData;
import com.somrpg.swordofmagic7.Core.SomCore;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WarpGateData implements WarpGate {
    private final String id;
    private final Location location;
    private final MapData nextMap;
    private final String trigger;
    private final String target;
    private Location targetLocation;
    private boolean isActive = true;

    private ParticleData particleData = new ParticleData(Particle.SPELL_WITCH);

    public WarpGateData(String id, Location location, String target, MapData nextMap, String trigger) {
        this.id = id;
        this.location = location;
        this.target = target;
        this.nextMap = nextMap;
        this.trigger = trigger;
        targetLocation = null;
    }

    public WarpGateData(String id, Location location, Location targetLocation, MapData nextMap, String trigger) {
        this.id = id;
        this.location = location;
        this.targetLocation = targetLocation;
        this.nextMap = nextMap;
        this.trigger = trigger;
        target = null;
    }

    @Override
    public void active() {
        isActive = true;
        particleData = new ParticleData(Particle.SPELL_WITCH);
    }

    @Override
    public void disable() {
        isActive = false;
        particleData = new ParticleData(Particle.REDSTONE);
    }

    @Override
    public void activeAtTime(int time) {
        active();
        SomCore.getSomTask().AsyncTaskLater(this::disable, time);
    }

    @Override
    public void usePlayer(@NotNull Player player) {
        /*
        if (getTrigger() != null) {
            if (getTrigger().equals("AusMineB1") && AusMineB1.Start()) return;
            else if (getTrigger().equals("AusMineB2") && AusMineB2.Start()) return;
            else if (getTrigger().equals("AusMineB3") && AusMineB3.Start()) return;
            else if (getTrigger().equals("AusMineB4") && AusMineB4.Start()) return;
        }
        */
        getNextMap().enter(player);
        if (getTarget() != null) targetLocation = WarpGateLoader.getWarpGate(getTarget()).getLocation();
        player.teleportAsync(getTargetLocation());
        SomSound.LevelUp.play(player);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public Location getTargetLocation() {
        return targetLocation;
    }

    @Override
    public String getTarget() {
        return target;
    }

    @Override
    public String getTrigger() {
        return null;
    }

    @Override
    public MapData getNextMap() {
        return nextMap;
    }
}
