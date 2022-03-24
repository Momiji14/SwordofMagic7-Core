package com.somrpg.swordofmagic7.Core.Inventory;

import com.somrpg.swordofmagic7.Core.Item.BaseItem;
import com.somrpg.swordofmagic7.Core.Item.SomEquipmentSlot;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface ItemInventory extends SomInventory, SomEquipmentInventory {
    void addContent(BaseItem baseItem);

    void removeEquipment(@NonNull SomEquipmentSlot slot);
}
