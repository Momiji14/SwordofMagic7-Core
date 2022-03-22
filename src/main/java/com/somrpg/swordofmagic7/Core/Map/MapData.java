package com.somrpg.swordofmagic7.Core.Map;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MapData implements MapDataInterface{

    private final String id;
    private final String display;
    private final String color;
    private final int level;
    private final boolean safe;
    private final Map<String, String> gatheringData = new HashMap<>();

    public MapData(String id, String display, String color, int level, boolean safe) {
        this.id = id;
        this.display = display;
        this.color = color;
        this.level = level;
        this.safe = safe;
    }

    @Override
    public void enter(@NotNull Player player) {
        PlayerData.getData(player).setMapData(this);
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
    public String getColor() {
        return color;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public boolean isSafe() {
        return safe;
    }

    @Override
    public Map<String, String> getGatheringData() {
        return gatheringData;
    }
}
