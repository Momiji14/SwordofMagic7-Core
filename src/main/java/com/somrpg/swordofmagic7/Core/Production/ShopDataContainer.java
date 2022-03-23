package com.somrpg.swordofmagic7.Core.Production;

import com.somrpg.swordofmagic7.Core.Production.Base.SomCraftItem;

import java.util.Map;

public class ShopDataContainer extends CraftDataContainer implements ShopData{

    public ShopDataContainer(String display, Map<Integer, SomCraftItem> craftItems) {
        super(display, craftItems);
    }
}
