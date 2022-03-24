package com.somrpg.swordofmagic7.Core.Player.Production;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Player.Production.Base.SomCraftItem;
import com.somrpg.swordofmagic7.Core.Player.Production.Base.SomRecipe;

import java.util.List;

public interface ShopItem extends SomCraftItem {

    static ShopItem create(SomRecipe recipes, SomItemStack somItemStack, int price) {
        return new ShopItemContainer(recipes, somItemStack, price);
    }

    void setPrice(int price);

    int getPrice();

    @Override
    List<String> toStringList();
}
