package com.somrpg.swordofmagic7.Core;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import org.bukkit.Location;

public class SomHologram {
    public static Hologram createHologram(String key, Location location) {
        return HologramsAPI.createHologram(SomCore.getPlugin(), location);
    }
}
