package com.somrpg.swordofmagic7.Core.Recipe;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;

import java.util.List;

public class ShopItem extends SomCraftItem {

    private int price;

    ShopItem(List<SomItemStack> recipes, SomItemStack somItemStack, int price) {
        super(recipes, somItemStack);
        this.price = price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

}
