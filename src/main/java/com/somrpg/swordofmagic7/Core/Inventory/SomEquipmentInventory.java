package com.somrpg.swordofmagic7.Core.Inventory;

import com.somrpg.swordofmagic7.Core.Item.EquipmentItem;
import com.somrpg.swordofmagic7.Core.Item.SomEquipmentSlot;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Map;

public interface SomEquipmentInventory extends SomInventory {

    Map<SomEquipmentSlot, EquipmentItem> getEquipmentSlot();

    default EquipmentItem getEquipment(@NonNull SomEquipmentSlot slot) {
        return getEquipmentSlot().get(slot);
    }

    default boolean hasEquipment(@NonNull SomEquipmentSlot slot) {
        return getEquipmentSlot().containsKey(slot);
    }

    void setEquipment(@NonNull EquipmentItem equipmentItem);
}
