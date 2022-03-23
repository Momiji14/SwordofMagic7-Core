package com.somrpg.swordofmagic7.Core.Production;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Production.Base.SomRecipe;

public interface ShopItem {

    static ShopItem create(SomRecipe recipes, SomItemStack somItemStack, int price) {
        return new ShopItemContainer(recipes, somItemStack, price);
    }

    void setPrice(int price);

    int getPrice();
}
