package com.somrpg.swordofmagic7.Core.Inventory;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class BaseInventory implements SomInventoryInterface {

    private final PlayerData playerData;
    private final PlayerInventory playerInventory;
    private final SomInventoryType inventoryType;
    private final List<SomItemStack> inventoryContent = new ArrayList<>();
    private int scroll = 0;

    public BaseInventory(@NonNull PlayerData playerData, @NonNull SomInventoryType inventoryType) {
        this.playerData = playerData;
        this.inventoryType = inventoryType;
        playerInventory = playerData.getPlayer().getInventory();
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    public Player getPlayer() {
        return getPlayerData().getPlayer();
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
        SomSound.Tick.play(getPlayer());
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
