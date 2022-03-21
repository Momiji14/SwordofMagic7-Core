package com.somrpg.swordofmagic7.Core.Inventory;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerDataInterface;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;

public class BaseInventory implements PlayerDataInterface, SomInventoryInterface {

    private final PlayerData playerData;
    private final PlayerInventory playerInventory;
    private final SomInventoryType inventoryType;
    private final List<SomItemStack> inventoryContent = new ArrayList<>();
    private int scroll = 0;

    public BaseInventory(PlayerData playerData, SomInventoryType inventoryType) {
        this.playerData = playerData;
        this.inventoryType = inventoryType;
        playerInventory = playerData.getPlayer().getInventory();
    }

    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }

    @Override
    public List<SomItemStack> getList() {
        return inventoryContent;
    }

    @Override
    public void setScroll(int scroll) {
        this.scroll = scroll;
    }

    @Override
    public void addScroll(int scroll) {
        setScroll((int) Math.max(0,Math.min(Math.ceil(getList().size()/9f)-2, getScroll() + scroll)));
    }

    @Override
    public int getScroll() {
        return scroll;
    }

    @Override
    public SomInventoryType getInventoryType() {
        return inventoryType;
    }

    @Override
    public PlayerInventory getInventory() {
        return playerInventory;
    }
}