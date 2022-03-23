package com.somrpg.swordofmagic7.Core.Player.Interface;

import com.somrpg.swordofmagic7.Core.Generic.DecoFormat;
import com.somrpg.swordofmagic7.Core.Generic.Entity.BaseEntity;
import com.somrpg.swordofmagic7.Core.Generic.Entity.BaseEntityContainer;
import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Player.Level.PlayerReqExp;
import com.somrpg.swordofmagic7.Core.Player.PlayerEntityContainer;

import java.util.Collection;

public interface PlayerEntity extends BaseEntity, PlayerStatusCalculation {

    PlayerEntityContainer getPlayerEntity();

    default void setLevel(int level) {
        getPlayerEntity().setLevel(level);
    }

    default int getLevel() {
        return getPlayerEntity().getLevel();
    }

    default void setExp(int exp) {
        getPlayerEntity().setExp(exp);
    }

    default int getExp() {
        return getPlayerEntity().getExp();
    }

    default String getExpPercentString() {
        return DecoFormat.ScaleDigit((double) getExp() / PlayerReqExp.getReqExp(getLevel()), GenericConfig.GenericDigit) + "%";
    }
}
