package com.somrpg.swordofmagic7.Core.Generic.Particle.LocationContainer;

import com.somrpg.swordofmagic7.Core.Generic.Particle.ParticleData;
import com.somrpg.swordofmagic7.Core.SomCore;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class BaseLocationContainer {
    private List<Location> container = new ArrayList<>();
    private ParticleData particleData;
    private BukkitTask animationTask;

    public BaseLocationContainer(ParticleData particleData, List<Location> container) {
        this.particleData = particleData;
        this.container = container;
    }

    public BaseLocationContainer setParticleData(ParticleData particleData) {
        this.particleData = particleData;
        return this;
    }

    public BaseLocationContainer setContainer(List<Location> container) {
        this.container = container;
        return this;
    }

    public void playAnimation(int millis) {
        if (animationTask != null) animationTask.cancel();
        animationTask = SomCore.getSomTask().AsyncTask(() -> {
            while (!animationTask.isCancelled()) {
                for (Location location : container) {
                    if (animationTask.isCancelled()) return;
                    particleData.spawn(location);
                    SomCore.getSomTask().sleepMillis(millis);
                }
            }
        });
    }
}
