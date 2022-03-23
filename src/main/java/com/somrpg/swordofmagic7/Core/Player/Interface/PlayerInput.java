package com.somrpg.swordofmagic7.Core.Player.Interface;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public interface PlayerInput {

    PlayerData getPlayerData();

    void onClickGUI(InventoryClickEvent event);
    void onCloseGUI(InventoryCloseEvent event);
}

