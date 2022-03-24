package com.somrpg.swordofmagic7.Core.Inventory;

import com.somrpg.swordofmagic7.Core.Item.BaseItem;

public interface ItemInventory extends SomInventory, SomEquipmentInventory {
    void addContent(BaseItem baseItem);
}
