package com.somrpg.swordofmagic7.Core.Production.Base;

public class CraftSlotContainer implements CraftSlot {

    private final SomCraftItem craftItem;

    CraftSlotContainer(SomCraftItem craftItem) {
        this.craftItem = craftItem;
    }

    @Override
    public SomCraftItem getCraftItem() {
        return craftItem;
    }
}
