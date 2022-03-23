package com.somrpg.swordofmagic7.Core.Player.Container;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerInput;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class PlayerInputContainer implements PlayerInput {

    private final PlayerData playerData;

    PlayerInputContainer(PlayerData playerData) {
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
        int slot = event.getSlot();
        ItemStack clickedItem = event.getCurrentItem();
        view.setCursor(null);
        if (clickedItem != null) {
            if (view.getTopInventory() == clickedInv) {
                getPlayerData().getUserMenu().onClick(event);
                getPlayerData().getSettingMenu().onClick(event);
                getPlayerData().getTeleportGateMenu().onClick(event);
            } else if (view.getBottomInventory() == clickedInv) {
                switch (slot) {
                    case 26 -> getPlayerData().getUserMenu().openGUI();
                    case 17 -> getPlayerData().getBaseViewInventory().addScroll(1);
                    case 35 -> getPlayerData().getBaseViewInventory().addScroll(-1);
                    default -> {
                        if (getPlayerData().getViewInventory().isItemInventory()) {

                        }
                    }
                }
            }
        }
    }

    @Override
    public void onCloseGUI(InventoryCloseEvent event) {
        SomSound.Close.play(getPlayerData().getPlayer());
        getPlayerData().getPlayerEntity().statusUpdate();
        getPlayerData().viewUpdate();
    }
}
