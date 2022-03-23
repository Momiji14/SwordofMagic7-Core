package com.somrpg.swordofmagic7.Core.Production;

import com.somrpg.swordofmagic7.Core.Production.Base.SomCraftItem;

import java.util.Map;

public interface CraftData {

    static CraftData create(String display, Map<Integer, SomCraftItem> craftItems) {
        return new CraftDataContainer(display, craftItems);
    }
}
