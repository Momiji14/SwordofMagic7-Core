package com.somrpg.swordofmagic7.Core.Generic;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerDataInterface;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface BaseGraphicalUserInterface extends PlayerDataInterface {

    String getTitle();

    int getSize();

    ItemStack[] getContent();

    default Inventory openGUI() {
        Inventory inv = Bukkit.createInventory(null, getSize()*9, Component.text(getTitle()));
        inv.setMaxStackSize(GenericConfig.MaxItemStackAmount);
        inv.setContents(getContent());
        getPlayer().openInventory(inv);
        SomSound.Open.play(getPlayer());
        return inv;
    }
}
