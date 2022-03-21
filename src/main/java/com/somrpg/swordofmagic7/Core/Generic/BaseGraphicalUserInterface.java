package com.somrpg.swordofmagic7.Core.Generic;

import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerDataInterface;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public interface BaseGraphicalUserInterface extends PlayerDataInterface {

    String getTitle();

    int getSize();

    ItemStack[] getContent();

    default Inventory openGUI() {
        Inventory inv = Bukkit.createInventory(null, getSize(), Component.text(getTitle()));
        inv.setMaxStackSize(GenericConfig.MaxItemStackAmount);
        inv.setContents(getContent());
        return inv;
    }

    default void onInventoryClick(InventoryView view, Inventory clickedInv, int slot, InventoryAction action) {
        if (view.title().toString().equals(getTitle())) {
            onClickTrigger(view, clickedInv, slot, action);
        }
    }

    void onClickTrigger(InventoryView view, Inventory clickedInv, int slot, InventoryAction action);
}
