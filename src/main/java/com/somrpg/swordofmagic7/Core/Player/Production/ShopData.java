package com.somrpg.swordofmagic7.Core.Player.Production;

import com.somrpg.swordofmagic7.Core.Player.Production.Base.CraftData;
import com.somrpg.swordofmagic7.Core.Player.Production.Base.CraftSlot;

import java.util.Map;

public interface ShopData extends CraftData {

    static ShopData create(String display, Map<Integer, CraftSlot> craftSlots) {
        return new ShopDataContainer(display, craftSlots);
    }

    String getPartition();

}
