package com.somrpg.swordofmagic7.Core.Generic.Item;

import com.somrpg.swordofmagic7.Core.Generic.Parameter.GenericStatus;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.GenericStatusContainer;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class EquipmentItem extends BaseItem implements Cloneable {
    private final GenericStatus equipmentStatus;
    private final EquipmentItemCategory equipmentItemCategory;
    private final SomEquipmentSlot equipmentSlot;
    private int plus = 0;
    private int reqLevel = 1;
    private int runeSlot = 1;
    private final List<RuneItem> rune = new ArrayList<>();

    public EquipmentItem(@NonNull BaseItem data, @NonNull EquipmentItemCategory equipmentItemCategory, @NonNull SomEquipmentSlot equipmentSlot) {
        super(data, data.getItemCategory(), data.getSell());
        this.equipmentSlot = equipmentSlot;
        this.equipmentItemCategory = equipmentItemCategory;
        this.equipmentStatus = new GenericStatusContainer();
        setMaterial(equipmentItemCategory.getMaterial());
    }

    public GenericStatus getStatusParameter() {
        return equipmentStatus;
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

    public List<RuneItem> getRune() {
        return rune;
    }

    public void setReqLevel(int reqLevel) {
        this.reqLevel = reqLevel;
    }

    public int getReqLevel() {
        return reqLevel;
    }

    public EquipmentItem cloneEquipment() {
       return clone();
    }

    @Override
    public EquipmentItem clone() {
        EquipmentItem clone = (EquipmentItem) super.clone();
        // TODO: このクローンが元の内部を変更できないようにミュータブルな状態をここにコピーします
        return clone;
    }
}
