package com.somrpg.swordofmagic7.Core.Inventory;

import com.somrpg.swordofmagic7.Core.Equipment.EquipmentItem;
import com.somrpg.swordofmagic7.Core.Equipment.SomEquipmentSlot;

import java.util.Map;

public interface SomEquipmentInventory extends SomInventoryInterface {

    Map<SomEquipmentSlot, EquipmentItem> getEquipmentSlot();

    default EquipmentItem getEquipment(SomEquipmentSlot slot) {
        return getEquipmentSlot().get(slot);
    }

    default boolean hasEquipment(SomEquipmentSlot slot) {
        return getEquipmentSlot().containsKey(slot);
    }
}
