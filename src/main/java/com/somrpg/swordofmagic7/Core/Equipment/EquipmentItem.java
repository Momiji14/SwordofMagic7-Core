package com.somrpg.swordofmagic7.Core.Equipment;

import com.somrpg.swordofmagic7.Core.Equipment.SomEquipmentSlot;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.StatusParameterInterface;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.StatusParameter;
import com.somrpg.swordofmagic7.Core.Item.BaseItem;
import com.somrpg.swordofmagic7.Core.Item.RuneItem;
import org.bukkit.Material;

import java.util.*;

public class EquipmentItem extends BaseItem {
    private final StatusParameter statusParameter;
    private final SomEquipmentSlot equipmentSlot;
    private int plus = 0;
    private int runeSlot = 1;
    private final List<UUID> rune = new ArrayList<>();

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

    public List<UUID> getRune() {
        return rune;
    }
}
