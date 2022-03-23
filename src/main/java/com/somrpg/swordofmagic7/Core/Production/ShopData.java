package com.somrpg.swordofmagic7.Core.Production;

import com.somrpg.swordofmagic7.Core.Production.Base.SomCraftItem;

import java.util.Map;

public interface ShopData {

    static ShopData create(String display, Map<Integer, SomCraftItem> craftItems) {
        return new ShopDataContainer(display, craftItems);
    }

}
