package com.somrpg.swordofmagic7.Core.Player.Interface;

import com.somrpg.swordofmagic7.Core.Entity.BaseEntity;
import com.somrpg.swordofmagic7.Core.Generic.DecoFormat;
import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Player.Container.PlayerEntityContainer;
import com.somrpg.swordofmagic7.Core.Player.Level.PlayerReqExp;

public interface PlayerEntity extends BaseEntity, PlayerStatusCalculation {

    PlayerEntityContainer getPlayerEntityContainer();

    default String getExpPercentString() {
        return DecoFormat.ScaleDigit((double) getExp() / PlayerReqExp.getReqExp(getLevel()), GenericConfig.GenericDigit) + "%";
    }
}
