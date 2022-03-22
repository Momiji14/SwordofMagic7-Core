package com.somrpg.swordofmagic7.Core.Generic;

import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface BaseGraphicalUser extends PlayerData {

    String getGUIDisplay();

    int getSize();

    ItemStack[] getContent();

    default Inventory openGUI() {
        Inventory inv = Bukkit.createInventory(null, getSize()*9, Component.text(getGUIDisplay()));
        inv.setMaxStackSize(GenericConfig.MaxItemStackAmount);
        inv.setContents(getContent());
        getPlayer().openInventory(inv);
        SomSound.Open.play(getPlayer());
        return inv;
    }
}
