package com.somrpg.swordofmagic7.Core.Player.Interface;

import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public interface PlayerInput extends PlayerData {

    void onClickGUI(InventoryClickEvent event);
    void onCloseGUI(InventoryCloseEvent event);
}

