package com.somrpg.swordofmagic7.Core.Player;

public class PlayerStatistics implements PlayerData {

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
