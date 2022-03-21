package com.somrpg.swordofmagic7.Core.Player;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerDataInterface;

public class PlayerStatistics implements PlayerDataInterface {

    private final PlayerData playerData;

    PlayerStatistics(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }

    private int playTime = 0;

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public void addPlayTime() {
        playTime++;
    }

    public int getPlayTime() {
        return playTime;
    }
}
