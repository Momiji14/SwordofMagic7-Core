package com.somrpg.swordofmagic7.Core.Player;

import com.somrpg.swordofmagic7.Core.Generic.Entity.BaseEntity;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerEntityInterface;

public class PlayerEntity extends BaseEntity implements PlayerEntityInterface {
    private final PlayerData playerData;

    public PlayerEntity(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }
}
