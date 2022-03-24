package com.somrpg.swordofmagic7.Core.Player.Production.Base;

public interface CraftSlot {

    static CraftSlot create(SomCraftItem craftItem) {
        return new CraftSlotContainer(craftItem);
    }

    SomCraftItem getCraftItem();
}
