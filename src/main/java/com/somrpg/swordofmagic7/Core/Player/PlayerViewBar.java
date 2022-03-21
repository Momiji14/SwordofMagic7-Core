package com.somrpg.swordofmagic7.Core.Player;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerViewBarInterface;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerViewBar implements PlayerViewBarInterface {

    private final PlayerData playerData;
    private final Player player;
    private final List<String> ScoreKey = new ArrayList<>();
    private final HashMap<String, List<String>> SideBar = new HashMap<>();

    PlayerViewBar(PlayerData playerData) {
        this.playerData = playerData;
        player = playerData.getPlayer();

        startTickUpdate();
    }

    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }

    @Override
    public void setSideBar(String key, List<String> data) {
        SideBar.put(key, data);
    }

    @Override
    public void resetSideBar(String key) {
        SideBar.remove(key);
    }

    @Override
    public HashMap<String, List<String>> getSideBar() {
        return SideBar;
    }

    @Override
    public List<String> getScoreKey() {
        return ScoreKey;
    }
}
