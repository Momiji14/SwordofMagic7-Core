package com.somrpg.swordofmagic7.Core.Player.Interface;

import com.somrpg.swordofmagic7.Core.Generic.DecoFormat;
import com.somrpg.swordofmagic7.Core.Generic.Entity.BaseEntity;
import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Player.Container.PlayerEntityContainer;
import com.somrpg.swordofmagic7.Core.Player.Level.PlayerReqExp;

public interface PlayerEntity extends BaseEntity, PlayerStatusCalculation {

    PlayerEntityContainer getPlayerEntityContainer();

    default void setLevel(int level) {
        getPlayerEntityContainer().setLevel(level);
    }
    default int getLevel() {
        return getPlayerEntityContainer().getLevel();
    }

    default void setExp(int exp) {
        getPlayerEntityContainer().setExp(exp);
    }
    default int getExp() {
        return getPlayerEntityContainer().getExp();
    }

    default String getExpPercentString() {
        return DecoFormat.ScaleDigit((double) getExp() / PlayerReqExp.getReqExp(getLevel()), GenericConfig.GenericDigit) + "%";
    }
}
