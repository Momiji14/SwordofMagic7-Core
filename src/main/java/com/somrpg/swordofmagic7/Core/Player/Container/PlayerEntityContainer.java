package com.somrpg.swordofmagic7.Core.Player.Container;

import com.somrpg.swordofmagic7.Core.Entity.BaseEntityContainer;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerEntity;

public class PlayerEntityContainer extends BaseEntityContainer implements PlayerEntity {
    private final PlayerData playerData;

    public PlayerEntityContainer(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override public PlayerData getPlayerData() {
        return playerData;
    }
    @Override public PlayerEntityContainer getPlayerEntityContainer() {
        return this;
    }
    @Override public BaseEntityContainer getBaseEntityContainer() {
        return this;
    }
}
