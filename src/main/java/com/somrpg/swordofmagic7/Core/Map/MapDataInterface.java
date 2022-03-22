package com.somrpg.swordofmagic7.Core.Map;

import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Map;

public interface MapDataInterface {

    void enter(@NonNull Player player);
    String getId();
    String getDisplay();
    String getColor();
    int getLevel();
    boolean isSafe();
    Map<String, String> getGatheringData();

}
