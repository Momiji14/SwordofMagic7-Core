package com.somrpg.swordofmagic7.Core.Player.Production.Base;

import java.util.Map;

public interface CraftData {

    static CraftData create(String display, Map<Integer, CraftSlot> craftSlots) {
        return new CraftDataContainer(display, craftSlots);
    }

    String getDisplay();

    Map<Integer, CraftSlot> getCraftSlots();
}
