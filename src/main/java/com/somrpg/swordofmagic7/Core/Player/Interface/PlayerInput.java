package com.somrpg.swordofmagic7.Core.Player.Interface;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Item.EquipmentItem;
import com.somrpg.swordofmagic7.Core.Item.SomEquipmentSlot;
import com.somrpg.swordofmagic7.Core.Player.Container.PlayerInputContainer;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public interface PlayerInput {

    PlayerData getPlayerData();

    PlayerInputContainer getPlayerInputContainer();

    default void onClickGUI(InventoryClickEvent event) {
        InventoryView view = event.getView();
        Inventory clickedInv = event.getClickedInventory();
        int slot = event.getSlot();
        ItemStack clickedItem = event.getCurrentItem();
        view.setCursor(null);
        if (clickedItem != null && clickedItem.getType() != Material.AIR) {
            if (view.getTopInventory() == clickedInv) {
                getPlayerData().getUserMenu().onClick(event);
                getPlayerData().getSettingMenu().onClick(event);
                getPlayerData().getTeleportGateMenu().onClick(event);
                getPlayerData().getShopDisplay().onClick(event);
                getPlayerData().getClassMenu().onClick(event);
            } else if (view.getBottomInventory() == clickedInv) {
                switch (slot) {
                    case 8 -> getPlayerData().getItemInventory().removeEquipment(SomEquipmentSlot.MainHand);
                    case 40 -> getPlayerData().getItemInventory().removeEquipment(SomEquipmentSlot.OffHand);
                    case 38 -> getPlayerData().getItemInventory().removeEquipment(SomEquipmentSlot.Armor);
                    case 26 -> getPlayerData().getUserMenu().openGUI();
                    case 17 -> {
                        if (getPlayerData().getViewInventory().isSkillSlot()) getPlayerData().getSkillSlot().ScrollUp();
                        else getPlayerData().getBaseViewInventory().addScroll(1);
                    }
                    case 35 -> {
                        if (getPlayerData().getViewInventory().isSkillSlot()) getPlayerData().getSkillSlot().ScrollDown();
                        else getPlayerData().getBaseViewInventory().addScroll(-1);
                    }
                    default -> {
                        if (getPlayerData().getViewInventory().isItemInventory()) {
                            int scroll = getPlayerData().getItemInventory().getScroll();
                            SomItemStack itemStack = getPlayerData().getItemInventory().getList().get(scroll*8+index(slot));
                            if (itemStack instanceof EquipmentItem item) {
                                getPlayerData().getItemInventory().setEquipment(item);
                            }
                        }
                    }
                }
            }
        }
    }

    default int index(int i) {
        i -= 9;
        if (i > 17) i--;
        if (i > 26) i--;
        return i;
    }

    default void onCloseGUI(InventoryCloseEvent event) {
        SomSound.Close.play(getPlayerData().getPlayer());
        getPlayerData().getPlayerEntity().statusUpdate();
        getPlayerData().viewUpdate();
    }

    default boolean isRightClickHold() {
        return getPlayerInputContainer().isRightClickHold();
    }

    default void setRightClickHold() {
        getPlayerInputContainer().setRightClickHold();
    }
}

