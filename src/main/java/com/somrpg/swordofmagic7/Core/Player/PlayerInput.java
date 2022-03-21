package com.somrpg.swordofmagic7.Core.Player;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerInputInterface;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class PlayerInput implements PlayerInputInterface {

    private final PlayerData playerData;

    PlayerInput(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }

    @Override
    public void onClickGUI(InventoryClickEvent event) {
        InventoryView view = event.getView();
        Inventory clickedInv = event.getClickedInventory();
        ClickType clickType = event.getClick();
        int slot = event.getSlot();
        ItemStack currentItem = event.getCurrentItem();
        if (currentItem != null) {
            if (view.getTopInventory() == clickedInv) {
                getPlayerData().getUserMenu().onClick(currentItem);
            } else if (view.getBottomInventory() == clickedInv) {
                switch (slot) {
                    case 26 -> getPlayerData().getUserMenu().openGUI();
                    case 17 -> getPlayerData().getBaseInventory(getPlayerData().getPlayerSetting().getViewInventory()).addScroll(1);
                    case 35 -> getPlayerData().getBaseInventory(getPlayerData().getPlayerSetting().getViewInventory()).addScroll(-1);
                }
            }
        }
    }

    @Override
    public void onCloseGUI(InventoryCloseEvent event) {

    }
}
