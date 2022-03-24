package com.somrpg.swordofmagic7.Core.Inventory;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.ViewableItemStack;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public interface SomInventory {

    ItemStack UpScrollIcon = ViewableItemStack.create("上へスクロール", Material.ITEM_FRAME).viewItemStack();
    ItemStack DownScrollIcon = ViewableItemStack.create("下へスクロール", Material.ITEM_FRAME).viewItemStack();
    ItemStack UserMenuIcon = ViewableItemStack.create("ユーザーメニュ", Material.BOOK, "ユーザーメニュを開きます").viewItemStack();

    List<SomItemStack> getList();
    void setScroll(int scroll);
    void addScroll(int scroll);
    int getScroll();
    SomInventoryType getInventoryType();
    PlayerInventory getInventory();
    PlayerData getPlayerData();

    default void removeContent(@NonNull SomItemStack content) {
        removeContent(content, content.getAmount());
    }

    default void removeContent(@NonNull SomItemStack content, int amount) {
        for (SomItemStack itemStack : getList()) {
            if (SomItemStack.equal(itemStack, content)) {
                itemStack.setAmount(itemStack.getAmount()-amount);
            }
        }
        getList().removeIf(somItemStack -> somItemStack.getAmount() <= 0);
        getPlayerData().viewUpdate();
    }

    default boolean hasContent(@NonNull SomItemStack content) {
        return hasContent(content, content.getAmount());
    }

    default boolean hasContent(@NonNull SomItemStack content, int amount) {
        for (SomItemStack itemStack : getList()) {
            if (SomItemStack.equal(itemStack, content)) {
                return itemStack.getAmount() >= amount;
            }
        }
        return false;
    }

    default SomItemStack getContent(@NonNull String Id) {
        for (SomItemStack itemStack : getList()) {
            if (itemStack.getId().equals(Id)) {
                return itemStack;
            }
        }
        return null;
    }

    default List<String> getContentsToString() {
        List<String> list = new ArrayList<>();
        for (SomItemStack itemStack : getList()) {
            list.add(itemStack.toDataString());
        }
        return list;
    }

    default void fromContentsFromString(@NonNull List<String> dataList) {
        getList().clear();
        for (String data : dataList) {
            getList().add(SomItemStack.fromDataString(data));
        }
    }

    void viewInventory();
}
