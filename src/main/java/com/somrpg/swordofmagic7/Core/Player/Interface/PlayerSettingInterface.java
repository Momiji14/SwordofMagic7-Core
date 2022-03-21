package com.somrpg.swordofmagic7.Core.Player.Interface;

import com.somrpg.swordofmagic7.Core.Player.Enum.*;

public interface PlayerSettingInterface extends PlayerDataInterface {
    void setPlayerStrafMode(PlayerStrafeMode playerStrafMode);
    PlayerStrafeMode getPlayerStrafeMode();

    void setPlayerPvPMode(PlayerPvPMode playerPvPMode);
    PlayerPvPMode getPlayerPvPMode();

    void setPlayMode(boolean playMode);
    boolean isPlayMode();

    void setCastMode(PlayerCastMode castMode);
    PlayerCastMode getCastMode();

    void setDamageLog(DamageLog damageLog);
    DamageLog getDamageLog();

    void setExpLog(ExpLog expLog);
    ExpLog getExpLog();

    void setDropLog(DropLog dropLog);
    DropLog getDropLog();
}
