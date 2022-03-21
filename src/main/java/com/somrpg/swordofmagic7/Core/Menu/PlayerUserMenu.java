package com.somrpg.swordofmagic7.Core.Menu;

import com.somrpg.swordofmagic7.Core.Player.PlayerData;

public class PlayerUserMenu implements UserMenuInterface {

    private final PlayerData playerData;
    public PlayerUserMenu(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public String getTitle() {
        return "§lユーザーメニュ";
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }

    @Override
    public void command(String[] args) {
        openGUI();
    }
}
