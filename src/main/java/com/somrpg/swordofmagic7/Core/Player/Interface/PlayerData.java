package com.somrpg.swordofmagic7.Core.Player.Interface;

import com.somrpg.swordofmagic7.Core.Generic.Entity.BaseEntityContainer;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.GenericStatus;
import com.somrpg.swordofmagic7.Core.Inventory.*;
import com.somrpg.swordofmagic7.Core.Map.MapData;
import com.somrpg.swordofmagic7.Core.Map.MapDataInterface;
import com.somrpg.swordofmagic7.Core.Menu.BaseMenu;
import com.somrpg.swordofmagic7.Core.Menu.PlayerSettingMenu;
import com.somrpg.swordofmagic7.Core.Menu.PlayerUserMenu;
import com.somrpg.swordofmagic7.Core.Menu.TeleportGateMenu;
import com.somrpg.swordofmagic7.Core.Player.*;
import com.somrpg.swordofmagic7.Core.Player.Container.*;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.List;

public interface PlayerData extends PlayerInput, PlayerEntity, PlayerSetting, PlayerBank, PlayerViewUpdate, PlayerOther {

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

    //Container
    BaseEntityContainer getBaseEntityContainer();
    PlayerEntityContainer getPlayerEntityContainer();
    PlayerSettingContainer getPlayerSettingContainer();
    PlayerStatisticsContainer getPlayerStatisticsContainer();
    PlayerBankContainer getPlayerBankContainer();

    //Interface
    PlayerEntity getPlayerEntity();
    PlayerSetting getPlayerSetting();
    PlayerStatistics getPlayerStatistics();
    PlayerBank getPlayerBank();
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

    //Menu
    PlayerUserMenu getUserMenu();
    PlayerSettingMenu getSettingMenu();
    TeleportGateMenu getTeleportGateMenu();


    //Map
    void setMapData(MapData mapData);
    MapDataInterface getMapData();

    void save();
    void load();
}
