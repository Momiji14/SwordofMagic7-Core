package com.somrpg.swordofmagic7.Core.Menu;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;

public class UserMenuContainer implements UserMenu {

    private final PlayerData playerData;

    public UserMenuContainer(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }
}
