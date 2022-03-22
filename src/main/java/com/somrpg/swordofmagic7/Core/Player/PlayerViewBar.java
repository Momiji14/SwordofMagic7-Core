package com.somrpg.swordofmagic7.Core.Player;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerViewBar implements com.somrpg.swordofmagic7.Core.Player.Interface.PlayerViewBar {

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
