package com.somrpg.swordofmagic7.Core.Player;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerInputInterface;

public class PlayerInput implements PlayerInputInterface {

    private final PlayerData playerData;

    PlayerInput(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }

}
