package com.somrpg.swordofmagic7.Core.Menu;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;

public class TeleportGateContainer implements TeleportGateMenu {

    private final PlayerData playerData;

    public TeleportGateContainer(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }
}
