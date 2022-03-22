package com.somrpg.swordofmagic7.Core.Particle;

import com.somrpg.swordofmagic7.Core.Player.PlayerList;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class ParticleData implements ParticleInterface {
    private Particle particle;
    private Particle.DustOptions dustOptions = new Particle.DustOptions(Color.BLACK, 1);
    private float speed = 0f;
    private Vector vector = new Vector();
    private Vector offset = new Vector(0, 0.15, 0);

    public ParticleData(Particle particle) {
        this.particle = particle;
    }

    @Override
    public void spawn(Location location) {
        if (particle != Particle.REDSTONE) {
            for (Player player : PlayerList.getNear(location, 48)) {
                player.spawnParticle(particle, location.clone().add(offset), 0, vector.getX(), vector.getY(), vector.getZ(), speed);
            }
        } else {
            for (Player player : PlayerList.getNear(location, 48)) {
                player.spawnParticle(particle, location.clone().add(offset), 0, vector.getX(), vector.getY(), vector.getZ(), speed, dustOptions);
            }
        }
    }

    @Override
    public ParticleData setParticle(Particle particle) {
        this.particle = particle;
        return this;
    }

    @Override
    public ParticleData setDustOptions(Particle.DustOptions dustOptions) {
        this.dustOptions = dustOptions;
        return this;
    }

    @Override
    public ParticleData setSpeed(float speed) {
        this.speed = speed;
        return this;
    }

    @Override
    public ParticleData setVector(Vector vector) {
        this.vector = vector;
        return this;
    }

    @Override
    public ParticleData setOffset(Vector offset) {
        this.offset = offset;
        return this;
    }
}
