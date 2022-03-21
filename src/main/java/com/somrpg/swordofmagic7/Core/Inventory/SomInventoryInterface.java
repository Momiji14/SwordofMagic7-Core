package com.somrpg.swordofmagic7.Core.Inventory;

import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;

public interface SomInventoryInterface {
    List<SomItemStack> getList();
    void setScroll(int scroll);
    void addScroll(int scroll);
    int getScroll();
    SomInventoryType getInventoryType();
    PlayerInventory getInventory();

    default void addContent(SomItemStack content) {
        getList().add(content);
    }

    default void removeContent(SomItemStack content) {
        getList().remove(content);
    }

    default boolean hasContent(String Id) {
        for (SomItemStack itemStack : getList()) {
            if (itemStack.getId().equals(Id)) {
                return true;
            }
        }
        return false;
    }

    default SomItemStack getContent(String Id) {
        for (SomItemStack itemStack : getList()) {
            if (itemStack.getId().equals(Id)) {
                return itemStack;
            }
        }
        return null;
    }

    default void viewInventory() {
        int index = 0;
        for (int slot = 9; slot < 36; slot++) {
            switch (slot) {
                case 17 -> getInventory().setItem(slot, GenericConfig.UpScrollIcon);
                case 26 -> getInventory().setItem(slot, GenericConfig.UserMenuIcon);
                case 35 -> getInventory().setItem(slot, GenericConfig.DownScrollIcon);
                default -> {
                    if (getList().size() > index) {
                        index++;
                        getInventory().setItem(slot, getList().get(index).viewItemStack());
                    } else {
                        getInventory().setItem(slot, null);
                    }
                }
            }
        }
    }
}
