package com.somrpg.swordofmagic7.Core.Player.Interface;

import com.somrpg.swordofmagic7.Core.Entity.BaseEntityContainer;
import com.somrpg.swordofmagic7.Core.Generic.Timer.SomTimer;
import com.somrpg.swordofmagic7.Core.Player.Inventory.*;
import com.somrpg.swordofmagic7.Core.Map.MapData;
import com.somrpg.swordofmagic7.Core.Map.MapDataInterface;
import com.somrpg.swordofmagic7.Core.Player.Container.*;
import com.somrpg.swordofmagic7.Core.Player.PlayerCharacon;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.entity.Player;

import java.util.List;

public interface PlayerData extends PlayerEntity, PlayerSetting, PlayerBank, PlayerViewUpdate, PlayerOther, PlayerInput, PlayerMenu, PlayerClass {

    static PlayerData getData(Player player) {
        return PlayerDataContainer.getData(player);
    }

    PlayerData getPlayerData();

    default PlayerDataContainer getPlayerDataContainer() {
        return getPlayerData().getPlayerDataContainer();
    }
    default Player getPlayer() {
        return getPlayerDataContainer().getPlayer();
    }

    default void sendMessage(String message, SomSound sound) {
        sendMessage(List.of(message.split("\n")), sound);
    }
    default void sendMessage(List<String> message, SomSound sound) {
        Player player = getPlayer();
        for (String msg : message) {
            player.sendMessage(msg);
        }
        sound.play(player);
    }

    //Timer
    SomTimer getTimer();

    //Container
    BaseEntityContainer getBaseEntityContainer();
    PlayerEntityContainer getPlayerEntityContainer();
    PlayerSettingContainer getPlayerSettingContainer();
    PlayerStatisticsContainer getPlayerStatisticsContainer();
    PlayerBankContainer getPlayerBankContainer();
    PlayerOtherContainer getPlayerOtherContainer();

    //Interface
    PlayerEntity getPlayerEntity();
    PlayerSetting getPlayerSetting();
    PlayerStatistics getPlayerStatistics();
    PlayerBank getPlayerBank();
    PlayerClass getPlayerClass();
    PlayerOther getPlayerOther();

    //Other
    PlayerCharacon getPlayerCharacon();
    PlayerDisplay getPlayerDisplay();
    PlayerInput getPlayerInput();

    //Inventory
    ItemInventory getItemInventory();
    RuneInventory getRuneInventory();
    PetInventory getPetInventory();
    default SomInventory getBaseInventory(SomInventoryType type) {
        switch (type) {
            case ItemInventory -> {
                return getItemInventory();
            }
            case RuneInventory -> {
                return getRuneInventory();
            }
            case PetInventory -> {
                return getPetInventory();
            }
        }
        return null;
    }
    default SomInventory getBaseViewInventory() {
        return getBaseInventory(getViewInventory());
    }

    SkillSlot getSkillSlot();

    //Map
    void setMapData(MapData mapData);
    MapDataInterface getMapData();

    void save();
    void load();
}
