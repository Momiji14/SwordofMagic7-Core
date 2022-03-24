package com.somrpg.swordofmagic7.Core.Player.Production;

import com.somrpg.swordofmagic7.Core.Generic.Item.BaseItem;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Player.Production.Base.SomCraftItemContainer;
import com.somrpg.swordofmagic7.Core.Player.Production.Base.SomRecipe;

import java.util.ArrayList;
import java.util.List;

public class ShopItemContainer extends SomCraftItemContainer implements ShopItem {

    private int price;

    ShopItemContainer(SomRecipe recipes, BaseItem item, int price) {
        super(recipes, item);
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
    public List<String> toStringList(boolean bold) {
        List<String> list = new ArrayList<>();
        list.add("§7・§e§l" + getPrice() + "メル");
        list.addAll(getRecipe().toStringList(bold));
        return list;
    }

}
