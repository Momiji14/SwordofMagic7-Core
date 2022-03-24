package com.somrpg.swordofmagic7.Core.Player.Production;

import com.somrpg.swordofmagic7.Core.Player.Production.Base.CraftDataContainer;
import com.somrpg.swordofmagic7.Core.Player.Production.Base.CraftSlot;

import java.util.Map;

public class ShopDataContainer extends CraftDataContainer implements ShopData {

    public ShopDataContainer(String display, Map<Integer, CraftSlot> craftSlots) {
        super(display, craftSlots);
    }

    @Override
    public String getPartition() {
        return "販売情報";
    }
}
