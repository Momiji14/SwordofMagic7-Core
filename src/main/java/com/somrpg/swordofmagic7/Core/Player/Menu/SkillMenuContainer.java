package com.somrpg.swordofmagic7.Core.Player.Menu;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SkillMenuContainer implements SkillMenu {

    private final PlayerData playerData;

    SkillMenuContainer(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public String getGUIDisplay() {
        return "スキルメニュー";
    }

    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }

    @Override
    public int getSize() {
        return 4;
    }

    @Override
    public ItemStack[] getContent() {

        return new ItemStack[0];
    }

    @Override
    public void onClick(Inventory clickedInv, ItemStack clickedItem, ClickType clickType, InventoryAction action, int slot) {

    }
}
