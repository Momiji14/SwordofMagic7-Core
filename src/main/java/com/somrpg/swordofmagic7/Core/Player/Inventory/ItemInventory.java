package com.somrpg.swordofmagic7.Core.Player.Inventory;

import com.somrpg.swordofmagic7.Core.Generic.Item.BaseItem;
import com.somrpg.swordofmagic7.Core.Generic.Item.EquipmentItem;
import com.somrpg.swordofmagic7.Core.Generic.Item.SomEquipmentSlot;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Map;

public interface ItemInventory extends SomInventory {
    void addContent(BaseItem baseItem);

    void removeEquipment(@NonNull SomEquipmentSlot slot);

    Map<SomEquipmentSlot, EquipmentItem> getEquipmentSlot();

    default EquipmentItem getEquipment(@NonNull SomEquipmentSlot slot) {
        return getEquipmentSlot().get(slot);
    }

    default boolean hasEquipment(@NonNull SomEquipmentSlot slot) {
        return getEquipmentSlot().containsKey(slot);
    }

    void setEquipment(@NonNull EquipmentItem equipmentItem);
}
