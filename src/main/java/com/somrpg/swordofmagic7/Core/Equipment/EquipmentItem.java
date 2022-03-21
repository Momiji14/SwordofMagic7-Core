package com.somrpg.swordofmagic7.Core.Equipment;

import com.somrpg.swordofmagic7.Core.Generic.Parameter.StatusParameter;
import com.somrpg.swordofmagic7.Core.Item.BaseItem;

import java.util.ArrayList;
import java.util.List;

public class EquipmentItem extends BaseItem {
    private final StatusParameter statusParameter;
    private final SomEquipmentSlot equipmentSlot;
    private int plus = 0;
    private int runeSlot = 1;
    private final List<String> rune = new ArrayList<>();

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

    public void setPlus(int plus) {
        this.plus = plus;
    }

    public int getPlus() {
        return plus;
    }

    public void setRuneSlot(int runeSlot) {
        this.runeSlot = runeSlot;
    }

    public int getRuneSlot() {
        return runeSlot;
    }

    public List<String> getRune() {
        return rune;
    }
}
