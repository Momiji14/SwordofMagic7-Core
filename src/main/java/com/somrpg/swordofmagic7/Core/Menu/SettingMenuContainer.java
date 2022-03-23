package com.somrpg.swordofmagic7.Core.Menu;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;

public class SettingMenuContainer  implements SettingMenu {

    private final PlayerData playerData;

    public SettingMenuContainer(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }
}
