package com.somrpg.swordofmagic7.Core.Player.Container;

import com.somrpg.swordofmagic7.Core.Generic.Entity.BaseEntityContainer;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerEntity;

public class PlayerEntityContainer extends BaseEntityContainer implements PlayerEntity {
    private final PlayerData playerData;
    private int level;
    private int exp;

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

    @Override public void setLevel(int level) {
        this.level = level;
    }
    @Override public int getLevel() {
        return level;
    }

    @Override public void setExp(int exp) {
        this.exp = exp;
    }
    @Override public int getExp() {
        return exp;
    }
}
