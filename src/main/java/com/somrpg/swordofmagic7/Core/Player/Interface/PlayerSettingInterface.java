package com.somrpg.swordofmagic7.Core.Player.Interface;

import com.somrpg.swordofmagic7.Core.Inventory.SomInventoryType;
import com.somrpg.swordofmagic7.Core.Player.Enum.*;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.PlayerSetting;

public interface PlayerSettingInterface {

    PlayerData getPlayerData();

    default PlayerSetting getPlayerSetting() {
        return getPlayerData().getPlayerSetting();
    }

    default void setPlayerStrafMode(PlayerStrafeMode playerStrafMode) {
        getPlayerSetting().setPlayerStrafMode(playerStrafMode);
    }
    default PlayerStrafeMode getPlayerStrafeMode() {
        return getPlayerSetting().getPlayerStrafeMode();
    }

    default void setPlayerPvPMode(PlayerPvPMode playerPvPMode) {
        getPlayerSetting().setPlayerPvPMode(playerPvPMode);
    }
    default PlayerPvPMode getPlayerPvPMode() {
        return getPlayerSetting().getPlayerPvPMode();
    }

    default void setPlayMode(boolean playMode) {
        getPlayerSetting().setPlayMode(playMode);
    }
    default boolean isPlayMode() {
        return getPlayerSetting().isPlayMode();
    }

    default void setCastMode(PlayerCastMode castMode) {
        getPlayerSetting().setCastMode(castMode);
    }
    default PlayerCastMode getCastMode() {
        return getPlayerSetting().getCastMode();
    }

    default void setDamageLog(DamageLog damageLog) {
        getPlayerSetting().setDamageLog(damageLog);
    }
    default DamageLog getDamageLog() {
        return getPlayerSetting().getDamageLog();
    }

    default void setExpLog(ExpLog expLog) {
        getPlayerSetting().setExpLog(expLog);
    }
    default ExpLog getExpLog() {
        return getPlayerSetting().getExpLog();
    }

    default void setDropLog(DropLog dropLog) {
        getPlayerSetting().setDropLog(dropLog);
    }
    default DropLog getDropLog() {
        return getPlayerSetting().getDropLog();
    }

    default void setViewInventory(SomInventoryType type) {
        getPlayerSetting().setViewInventory(type);
    }
    default SomInventoryType getViewInventory() {
        return getPlayerSetting().getViewInventory();
    }

    default void setViewDigit(int digit) {
        getPlayerSetting().setViewDigit(digit);
    }

    default int getViewDigit() {
        return getPlayerSetting().getViewDigit();
    }
}
