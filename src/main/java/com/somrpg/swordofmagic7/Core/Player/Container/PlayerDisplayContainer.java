package com.somrpg.swordofmagic7.Core.Player.Container;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerDisplay;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerDisplayContainer implements PlayerDisplay {

    private final PlayerData playerData;
    private final Player player;
    private final List<String> ScoreKey = new ArrayList<>();
    private final HashMap<String, List<String>> SideBar = new HashMap<>();

    PlayerDisplayContainer(PlayerData playerData) {
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
