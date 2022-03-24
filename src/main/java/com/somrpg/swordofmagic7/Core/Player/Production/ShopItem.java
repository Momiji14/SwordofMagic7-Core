package com.somrpg.swordofmagic7.Core.Player.Production;

import com.somrpg.swordofmagic7.Core.Generic.Item.BaseItem;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Player.Production.Base.SomCraftItem;
import com.somrpg.swordofmagic7.Core.Player.Production.Base.SomRecipe;

import java.util.Base64;
import java.util.List;

public interface ShopItem extends SomCraftItem {

    static ShopItem create(SomRecipe recipes, BaseItem item, int price) {
        return new ShopItemContainer(recipes, item, price);
    }

    void setPrice(int price);

    int getPrice();

    @Override
    List<String> toStringList(boolean bold);
}
