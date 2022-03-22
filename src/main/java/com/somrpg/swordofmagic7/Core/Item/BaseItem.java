package com.somrpg.swordofmagic7.Core.Item;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;

import static com.somrpg.swordofmagic7.Core.Generic.DecoContent.decoLore;
import static com.somrpg.swordofmagic7.Core.Generic.DecoContent.decoPartition;

public class BaseItem extends SomItemStack {
    private final ItemCategory itemCategory;
    private final int sell;

    public BaseItem(@NonNull SomItemStack data, @NonNull ItemCategory itemCategory, int sell) {
        super(data.getId(), data.getDisplay(), data.getMaterial(), data.getLore());
        this.itemCategory = itemCategory;
        this.sell = sell;

        getLore().add(decoPartition("アイテム情報"));
        getLore().add(decoLore("カテゴリ") + getItemCategory().getDisplay());
        getLore().add(decoLore("売値") + getSell());
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
}
