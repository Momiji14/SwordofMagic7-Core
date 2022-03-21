package com.somrpg.swordofmagic7.Core.Equipment;

import com.somrpg.swordofmagic7.Core.Equipment.SomEquipmentSlot;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.StatusParameterInterface;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.StatusParameter;
import com.somrpg.swordofmagic7.Core.Item.BaseItem;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EquipmentItem extends BaseItem {
    private final StatusParameter statusParameter;
    private final SomEquipmentSlot equipmentSlot;

    public EquipmentItem(BaseItem data, SomEquipmentSlot equipmentSlot) {
        super(data, data.getItemCategory(), data.getSell());
        this.equipmentSlot = equipmentSlot;
        this.statusParameter = new StatusParameter();
    }

    public StatusParameter getStatusParameter() {
        return statusParameter;
    }

    public SomEquipmentSlot getEquipmentSlot() {
        return equipmentSlot;
    }
}
