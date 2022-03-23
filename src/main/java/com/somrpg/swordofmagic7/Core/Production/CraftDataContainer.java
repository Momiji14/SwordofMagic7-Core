package com.somrpg.swordofmagic7.Core.Production;

import com.somrpg.swordofmagic7.Core.Production.Base.SomCraftItem;

import java.util.Map;

public class CraftDataContainer implements CraftData {
    private final String display;
    private final Map<Integer, SomCraftItem> craftItems;

    CraftDataContainer(String display, Map<Integer, SomCraftItem> craftItems) {
        this.display = display;
        this.craftItems = craftItems;
    }

    public String getDisplay() {
        return display;
    }

    public Map<Integer, SomCraftItem> getCraftItems() {
        return craftItems;
    }
}
