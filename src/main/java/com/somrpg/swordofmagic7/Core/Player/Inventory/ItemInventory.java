package com.somrpg.swordofmagic7.Core.Player.Inventory;

import com.somrpg.swordofmagic7.Core.Generic.Item.BaseItem;
import com.somrpg.swordofmagic7.Core.Generic.Item.SomEquipmentSlot;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface ItemInventory extends SomInventory, SomEquipmentInventory {
    void addContent(BaseItem baseItem);

    void removeEquipment(@NonNull SomEquipmentSlot slot);
}
