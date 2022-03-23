package com.somrpg.swordofmagic7.Core.Production;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Production.Base.SomCraftItemContainer;
import com.somrpg.swordofmagic7.Core.Production.Base.SomRecipe;

import java.util.ArrayList;
import java.util.List;

public class ShopItemContainer extends SomCraftItemContainer implements ShopItem {

    private int price;

    ShopItemContainer(SomRecipe recipes, SomItemStack somItemStack, int price) {
        super(recipes, somItemStack);
        this.price = price;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public List<String> toStringList() {
        List<String> list = new ArrayList<>();
        list.add("§7・§e§l" + getPrice() + "メル");
        list.addAll(getRecipe().toStringList());
        return list;
    }

}
