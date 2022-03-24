package com.somrpg.swordofmagic7.Core.Player.Menu;

import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface BaseMenu {

    String getGUIDisplay();

    default Component getGUIDisplayComponent() {
        return Component.text("§l" + getGUIDisplay());
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
        if (!getPlayer().getOpenInventory().title().equals(getGUIDisplayComponent())) {
            getPlayer().openInventory(inv);
            SomSound.Open.play(getPlayer());
        }
        openProcess();
        return inv;
    }

    default void openProcess() {

    }

    default void review() {
        if (getPlayer().getOpenInventory().title().equals(getGUIDisplayComponent())) {
            getPlayer().getOpenInventory().getTopInventory().setContents(getContent());
        }
    }

    default void onClick(InventoryClickEvent event) {
        if (event.getView().title().equals(getGUIDisplayComponent())) {
            onClick(event.getClickedInventory(), event.getCurrentItem(), event.getClick(), event.getAction(), event.getSlot());
        }
    }

    void onClick(Inventory clickedInv, ItemStack clickedItem, ClickType clickType, InventoryAction action, int slot);
}
