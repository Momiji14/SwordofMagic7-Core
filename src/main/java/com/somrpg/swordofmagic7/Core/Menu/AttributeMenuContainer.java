package com.somrpg.swordofmagic7.Core.Menu;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;

public class AttributeMenuContainer implements AttributeMenu {

    private final PlayerData playerData;

    public AttributeMenuContainer(PlayerData playerData) {
        this.playerData = playerData;
    }
    
    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }
}
