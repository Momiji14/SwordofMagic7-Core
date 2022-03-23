package com.somrpg.swordofmagic7.Core.Player.Interface;

import com.somrpg.swordofmagic7.Core.Inventory.SomInventoryType;
import com.somrpg.swordofmagic7.Core.Player.Enum.*;
import com.somrpg.swordofmagic7.Core.Player.Container.PlayerSettingContainer;
import org.bukkit.configuration.file.FileConfiguration;

public interface PlayerSetting {

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

    default void setPlayerCastMode(PlayerCastMode castMode) {
        getPlayerSetting().setPlayerCastMode(castMode);
    }
    default PlayerCastMode getPlayerCastMode() {
        return getPlayerSetting().getPlayerCastMode();
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

    void save(FileConfiguration data);
    void load(FileConfiguration data);
}
