package com.somrpg.swordofmagic7.Core.Generic;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerDataInterface;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
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
}
