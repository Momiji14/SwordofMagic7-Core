package com.somrpg.swordofmagic7.Core.Player;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerOther;

public class PlayerOtherContainer implements PlayerOther {

    private final PlayerData playerData;

    PlayerOtherContainer(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public PlayerOtherContainer getPlayerOtherContainer() {
        return this;
    }

    @Override
    public String getNick() {
        return null;
    }
}
