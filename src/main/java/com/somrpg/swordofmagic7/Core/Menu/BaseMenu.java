package com.somrpg.swordofmagic7.Core.Menu;

import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface BaseMenu {

    String getGUIDisplay();

    default Component getGUIDisplayComponent() {
        return Component.text(getGUIDisplay());
    }

    PlayerData getPlayerData();

    default Player getPlayer() {
        return getPlayerData().getPlayer();
    }

    int getSize();

    ItemStack[] getContent();

    default Inventory openGUI() {
        Inventory inv = Bukkit.createInventory(null, getSize()*9, getGUIDisplayComponent());
        inv.setMaxStackSize(GenericConfig.MaxItemStackAmount);
        inv.setContents(getContent());
        getPlayer().openInventory(inv);
        SomSound.Open.play(getPlayer());
        return inv;
    }

    default void onClick(InventoryClickEvent event) {
        if (event.getView().title().equals(getGUIDisplayComponent())) {
            onClick(event.getClickedInventory(), event.getCurrentItem(), event.getSlot());
        }
    }

    void onClick(Inventory clickedInv, ItemStack clickedItem, int slot);
}
