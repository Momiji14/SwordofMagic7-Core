package com.somrpg.swordofmagic7.Core.Production.Base;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import org.bukkit.inventory.ItemStack;

public interface SomCraftItem extends SomRecipe {

    static SomCraftItem create(SomRecipe recipe, SomItemStack somItemStack) {
        return new SomCraftItemContainer(recipe, somItemStack);
    }

    SomItemStack getSomItemStack();

    ItemStack viewItemStack();

    String getPartition();
}
