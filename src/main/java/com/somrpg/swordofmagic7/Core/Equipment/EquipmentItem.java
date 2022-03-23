package com.somrpg.swordofmagic7.Core.Equipment;

import com.somrpg.swordofmagic7.Core.Generic.Parameter.GenericStatus;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.GenericStatusContainer;
import com.somrpg.swordofmagic7.Core.Item.BaseItem;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class EquipmentItem extends BaseItem {
    private final GenericStatus genericStatus;
    private final EquipmentItemCategory equipmentItemCategory;
    private final SomEquipmentSlot equipmentSlot;
    private int plus = 0;
    private int runeSlot = 1;
    private final List<String> rune = new ArrayList<>();

    public EquipmentItem(@NonNull BaseItem data, @NonNull EquipmentItemCategory equipmentItemCategory, @NonNull SomEquipmentSlot equipmentSlot) {
        super(data, data.getItemCategory(), data.getSell());
        this.equipmentSlot = equipmentSlot;
        this.equipmentItemCategory = equipmentItemCategory;
        this.genericStatus = new GenericStatusContainer();
        setMaterial(equipmentItemCategory.getMaterial());
    }

    public GenericStatus getStatusParameter() {
        return genericStatus;
    }

    public EquipmentItemCategory getEquipmentItemCategory() {
        return equipmentItemCategory;
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
