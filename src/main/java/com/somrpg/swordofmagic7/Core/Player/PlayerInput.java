package com.somrpg.swordofmagic7.Core.Player;

import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class PlayerInput implements com.somrpg.swordofmagic7.Core.Player.Interface.PlayerInput {

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
        view.setCursor(null);
        if (currentItem != null) {
            if (view.getTopInventory() == clickedInv) {
                getUserMenu().onClick(currentItem);
            } else if (view.getBottomInventory() == clickedInv) {
                switch (slot) {
                    case 26 -> getUserMenu().openGUI();
                    case 17 -> getBaseViewInventory().addScroll(1);
                    case 35 -> getBaseViewInventory().addScroll(-1);
                    default -> {
                        if (getViewInventory().isItemInventory()) {

                        }
                    }
                }
            }
        }
    }

    @Override
    public void onCloseGUI(InventoryCloseEvent event) {
        SomSound.Close.play(getPlayer());
        getPlayerData().getPlayerEntity().statusUpdate();
        getPlayerData().viewUpdate();
    }
}
