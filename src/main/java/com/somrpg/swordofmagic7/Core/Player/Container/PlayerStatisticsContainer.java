package com.somrpg.swordofmagic7.Core.Player.Container;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerStatistics;

public class PlayerStatisticsContainer implements PlayerStatistics {

    private final PlayerData playerData;

    PlayerStatisticsContainer(PlayerData playerData) {
        this.playerData = playerData;
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    private int playTime = 0;

    @Override
    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    @Override
    public void addPlayTime() {
        playTime++;
    }

    @Override
    public int getPlayTime() {
        return playTime;
    }
}
