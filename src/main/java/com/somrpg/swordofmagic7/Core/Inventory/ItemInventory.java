package com.somrpg.swordofmagic7.Core.Inventory;

import com.somrpg.swordofmagic7.Core.Item.BaseItem;
import com.somrpg.swordofmagic7.Core.Item.RuneItem;

public interface ItemInventory extends SomInventory, SomEquipmentInventory {
    void addContent(BaseItem baseItem);
}
