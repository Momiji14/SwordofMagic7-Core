package com.somrpg.swordofmagic7.Core.Listener;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        PlayerData playerData = PlayerData.getData(player);
        if (playerData.getPlayerSetting().isPlayMode()) {
            playerData.getPlayerInput().onClickGUI(event);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        PlayerData playerData = PlayerData.getData(player);
        if (playerData.getPlayerSetting().isPlayMode()) {
            playerData.getPlayerInput().onCloseGUI(event);
            playerData.getPlayerEntity().statusUpdate();
        }
    }

}
