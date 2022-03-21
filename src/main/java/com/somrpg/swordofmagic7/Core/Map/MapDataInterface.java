package com.somrpg.swordofmagic7.Core.Map;

import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import org.bukkit.entity.Player;

import java.util.Map;

public interface MapDataInterface {

    void enter(Player player);
    String getId();
    String getDisplay();
    String getColor();
    int getLevel();
    boolean isSafe();
    Map<String, String> getGatheringData();

}
