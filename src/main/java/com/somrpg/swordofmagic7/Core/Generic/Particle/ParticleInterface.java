package com.somrpg.swordofmagic7.Core.Generic.Particle;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface ParticleInterface {

    void spawn(@NonNull Location location);

    ParticleData setParticle(@NonNull Particle particle);
    ParticleData setDustOptions(Particle.DustOptions dustOptions);
    ParticleData setSpeed(float speed);
    ParticleData setVector(@NonNull Vector vector);
    ParticleData setOffset(@NonNull Vector offset);

    default ParticleData setOffsetY(double offsetY) {
        return setOffset(new Vector(0, offsetY, 0));
    }

}
