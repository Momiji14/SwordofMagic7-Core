package com.somrpg.swordofmagic7.Core.Generic.Item;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;

public class BaseItem extends SomItemStack implements Cloneable {
    private final ItemCategory itemCategory;
    private final int sell;

    public BaseItem(@NonNull SomItemStack data, @NonNull ItemCategory itemCategory, int sell) {
        super(data.getId(), data.getDisplay(), data.getMaterial(), data.getLore());
        this.itemCategory = itemCategory;
        this.sell = sell;
    }

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public int getSell() {
        return sell;
    }

    public SomItemStack getSomItemStack() {
        return this;
    }

    public BaseItem cloneBaseItem() {
        try {
            return (BaseItem) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
