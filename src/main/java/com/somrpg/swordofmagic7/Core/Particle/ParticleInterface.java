package com.somrpg.swordofmagic7.Core.Particle;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public interface ParticleInterface {

    void spawn(Location location);

    ParticleData setParticle(Particle particle);
    ParticleData setDustOptions(Particle.DustOptions dustOptions);
    ParticleData setSpeed(float speed);
    ParticleData setVector(Vector vector);
    ParticleData setOffset(Vector offset);

    default ParticleData setOffsetY(double offsetY) {
        return setOffset(new Vector(0, offsetY, 0));
    }

}
