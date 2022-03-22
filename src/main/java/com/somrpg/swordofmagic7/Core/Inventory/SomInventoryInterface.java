package com.somrpg.swordofmagic7.Core.Inventory;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.ViewableItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public interface SomInventoryInterface {

    ItemStack UpScrollIcon = ViewableItemStack.create("上へスクロール", Material.ITEM_FRAME).viewItemStack();
    ItemStack DownScrollIcon = ViewableItemStack.create("下へスクロール", Material.ITEM_FRAME).viewItemStack();
    ItemStack UserMenuIcon = ViewableItemStack.create("ユーザーメニュ", Material.BOOK, "ユーザーメニュを開きます").viewItemStack();

    List<SomItemStack> getList();
    void setScroll(int scroll);
    void addScroll(int scroll);
    int getScroll();
    SomInventoryType getInventoryType();
    PlayerInventory getInventory();

    default void addContent(@NonNull SomItemStack content) {
        getList().add(content);
    }

    default void removeContent(@NonNull SomItemStack content) {
        getList().remove(content);
    }

    default boolean hasContent(@NonNull String Id) {
        for (SomItemStack itemStack : getList()) {
            if (itemStack.getId().equals(Id)) {
                return true;
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

    default void viewInventory() {
        int index = 0;
        for (int slot = 9; slot < 36; slot++) {
            switch (slot) {
                case 17 -> getInventory().setItem(slot, UpScrollIcon);
                case 26 -> getInventory().setItem(slot, UserMenuIcon);
                case 35 -> getInventory().setItem(slot, DownScrollIcon);
                default -> {
                    if (getList().size() > index) {
                        getInventory().setItem(slot, getList().get(index).viewItemStack());
                        index++;
                    } else {
                        getInventory().setItem(slot, null);
                    }
                }
            }
        }
    }
}
