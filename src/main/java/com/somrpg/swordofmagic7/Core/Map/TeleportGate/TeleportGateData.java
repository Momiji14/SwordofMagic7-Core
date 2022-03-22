package com.somrpg.swordofmagic7.Core.Map.TeleportGate;

import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.ViewableItemStack;
import com.somrpg.swordofmagic7.Core.Map.MapData;
import com.somrpg.swordofmagic7.Core.Particle.ParticleData;
import com.somrpg.swordofmagic7.Core.SomCore;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class TeleportGateData implements TeleportGate {
    private final String id;
    private final String display;
    private final Material material;
    private final String title;
    private final String subTitle;
    private final Location location;
    private final MapData mapData;
    private boolean defaultActive;

    public TeleportGateData(String id, String display, Material material, String title, String subTitle, Location location, MapData mapData, boolean defaultActive) {
        this.id = id;
        this.display = display;
        this.material = material;
        this.title = title;
        this.subTitle = subTitle;
        this.location = location;
        this.mapData = mapData;
        this.defaultActive = defaultActive;
    }

    public ItemStack viewItemStack() {
        return ViewableItemStack.create(display, material).viewItemStack();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDisplay() {
        return display;
    }

    @Override
    public Material getMaterial() {
        return material;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSubTitle() {
        return subTitle;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public MapData getMapData() {
        return mapData;
    }

    @Override
    public boolean isDefaultActive() {
        return defaultActive;
    }
}
