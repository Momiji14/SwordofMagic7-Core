package com.somrpg.swordofmagic7.Core.Player.Production.Base;

import com.somrpg.swordofmagic7.Core.Generic.Item.BaseItem;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface SomCraftItem {

    static SomCraftItem create(SomRecipe recipe, BaseItem item) {
        return new SomCraftItemContainer(recipe, item);
    }

    BaseItem getItem();

    ItemStack viewItemStack();

    String getPartition();

    SomRecipe getRecipe();

    List<String> toStringList(boolean bold);
}
