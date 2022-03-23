package com.somrpg.swordofmagic7.Core.Player.Interface;

import com.somrpg.swordofmagic7.Core.Inventory.SomInventoryType;
import com.somrpg.swordofmagic7.Core.Player.Enum.*;
import com.somrpg.swordofmagic7.Core.Player.Container.PlayerSettingContainer;
import org.bukkit.configuration.file.FileConfiguration;

public interface PlayerSetting {

    PlayerData getPlayerData();

    default PlayerSettingContainer getPlayerSettingContainer() {
        return getPlayerData().getPlayerSettingContainer();
    }

    default void setPlayerStrafMode(PlayerStrafeMode playerStrafMode) {
        getPlayerSettingContainer().setPlayerStrafMode(playerStrafMode);
    }
    default PlayerStrafeMode getPlayerStrafeMode() {
        return getPlayerSettingContainer().getPlayerStrafeMode();
    }

    default void setPlayerPvPMode(PlayerPvPMode playerPvPMode) {
        getPlayerSettingContainer().setPlayerPvPMode(playerPvPMode);
    }
    default PlayerPvPMode getPlayerPvPMode() {
        return getPlayerSettingContainer().getPlayerPvPMode();
    }

    default void setPlayMode(boolean playMode) {
        getPlayerSettingContainer().setPlayMode(playMode);
    }
    default boolean isPlayMode() {
        return getPlayerSettingContainer().isPlayMode();
    }

    default void setPlayerCastMode(PlayerCastMode castMode) {
        getPlayerSettingContainer().setPlayerCastMode(castMode);
    }
    default PlayerCastMode getPlayerCastMode() {
        return getPlayerSettingContainer().getPlayerCastMode();
    }

    default void setDamageLog(DamageLog damageLog) {
        getPlayerSettingContainer().setDamageLog(damageLog);
    }
    default DamageLog getDamageLog() {
        return getPlayerSettingContainer().getDamageLog();
    }

    default void setExpLog(ExpLog expLog) {
        getPlayerSettingContainer().setExpLog(expLog);
    }
    default ExpLog getExpLog() {
        return getPlayerSettingContainer().getExpLog();
    }

    default void setDropLog(DropLog dropLog) {
        getPlayerSettingContainer().setDropLog(dropLog);
    }
    default DropLog getDropLog() {
        return getPlayerSettingContainer().getDropLog();
    }

    default void setViewInventory(SomInventoryType type) {
        getPlayerSettingContainer().setViewInventory(type);
    }
    default SomInventoryType getViewInventory() {
        return getPlayerSettingContainer().getViewInventory();
    }

    default void setViewDigit(int digit) {
        getPlayerSettingContainer().setViewDigit(digit);
    }

    default int getViewDigit() {
        return getPlayerSettingContainer().getViewDigit();
    }

    default void saveSetting(FileConfiguration data) {
        getPlayerSettingContainer().loadSetting(data);
    }
    default void loadSetting(FileConfiguration data) {
        getPlayerSettingContainer().loadSetting(data);
    }
}
