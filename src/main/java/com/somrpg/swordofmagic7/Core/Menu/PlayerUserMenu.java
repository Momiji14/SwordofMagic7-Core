package com.somrpg.swordofmagic7.Core.Menu;

import com.somrpg.swordofmagic7.Core.Generic.BaseGraphicalUserInterface;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PlayerUserMenu implements UserMenuInterface, CommandExecutor {

    private final PlayerData playerData;
    public PlayerUserMenu(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public String getTitle() {
        return "§lユーザーメニュ";
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void onClickTrigger(InventoryView view, Inventory clickedInv, int slot, InventoryAction action) {

    }

    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("menu")) {
            openGUI();
            return true;
        }
        return false;
    }
}
