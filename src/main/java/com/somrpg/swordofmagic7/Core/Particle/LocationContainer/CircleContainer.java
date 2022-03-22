package com.somrpg.swordofmagic7.Core.Particle.LocationContainer;

import com.somrpg.swordofmagic7.Core.Particle.ParticleData;
import org.bukkit.Location;

import java.util.List;

public class CircleContainer extends BaseLocationContainer{

    public CircleContainer(ParticleData particleData, List<Location> container, Location location, double radius, double density) {
        super(particleData, container);
        for (double i=0; i < 2 * Math.PI ; i += density) {
            double x = Math.cos(i) * radius;
            double z = Math.sin(i) * radius;
            container.add(location.clone().add(x, 0 ,z));
        }
        setContainer(container);
    }
}
