package com.somrpg.swordofmagic7.Core.Player;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerInputInterface;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerInputListener;

public class PlayerInput implements PlayerInputInterface {

    private final PlayerData playerData;
    private PlayerInputListener listener = null;

    PlayerInput(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }

    public void registerListener(PlayerInputListener listener) {
        this.listener = listener;
    }
}
