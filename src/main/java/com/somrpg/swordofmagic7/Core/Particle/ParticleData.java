package com.somrpg.swordofmagic7.Core.Particle;

import com.somrpg.swordofmagic7.Core.Player.PlayerList;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

public class ParticleData implements ParticleInterface {
    private Particle particle;
    private Particle.DustOptions dustOptions = new Particle.DustOptions(Color.BLACK, 1);
    private float speed = 0f;
    private Vector vector = new Vector();
    private Vector offset = new Vector(0, 0.15, 0);

    public ParticleData(@NonNull Particle particle) {
        this.particle = particle;
    }

    @Override
    public void spawn(@NonNull Location location) {
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
    public ParticleData setParticle(@NotNull Particle particle) {
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
    public ParticleData setVector(@NotNull Vector vector) {
        this.vector = vector;
        return this;
    }

    @Override
    public ParticleData setOffset(@NotNull Vector offset) {
        this.offset = offset;
        return this;
    }
}
