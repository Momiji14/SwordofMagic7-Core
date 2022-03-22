package com.somrpg.swordofmagic7.Core.Player.Interface;

public interface PlayerViewUpdate {

    PlayerData getPlayerData();

    default void viewUpdate() {
        switch (getPlayerData().getPlayerSetting().getViewInventory()) {
            case ItemInventory -> getPlayerData().getItemInventory().viewInventory();
            case RuneInventory -> getPlayerData().getRuneInventory().viewInventory();
            case PetInventory -> getPlayerData().getPetInventory().viewInventory();
        }
    }
}
