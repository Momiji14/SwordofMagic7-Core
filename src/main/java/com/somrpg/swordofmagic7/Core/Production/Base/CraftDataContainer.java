package com.somrpg.swordofmagic7.Core.Production.Base;

import java.util.Map;

public class CraftDataContainer implements CraftData {
    private final String display;
    private final Map<Integer, CraftSlot> craftSlots;

    protected CraftDataContainer(String display, Map<Integer, CraftSlot> craftSlots) {
        this.display = display;
        this.craftSlots = craftSlots;
    }

    @Override
    public String getDisplay() {
        return display;
    }

    @Override
    public Map<Integer, CraftSlot> getCraftSlots() {
        return craftSlots;
    }
}
