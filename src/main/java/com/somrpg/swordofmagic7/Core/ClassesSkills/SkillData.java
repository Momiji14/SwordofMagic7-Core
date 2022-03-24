package com.somrpg.swordofmagic7.Core.ClassesSkills;

import com.somrpg.swordofmagic7.Core.Item.EquipmentItemCategory;
import org.bukkit.Material;

import java.util.List;

public interface SkillData {

    static SkillData create(String id, Material material, String display, List<String> lore, SkillType skillType, int reqLevel, List<SkillParameter> skillParameters, List<EquipmentItemCategory> reqMainHand, List<EquipmentItemCategory> reqOffHand) {
        return new SkillDataContainer(id, material, display, skillType, lore, reqLevel, skillParameters, reqMainHand, reqOffHand);
    }

    String getId();

    Material getMaterial();

    String getDisplay();

    List<String> getLore();

    SkillType getSkillType();

    List<SkillParameter> getSkillParameters();

    double getValue(int i);

    int getValueInt(int i);

    void setMana(int mana);

    int getMana();

    void setStack(int stack);

    int getStack();

    void setCastTime(int castTime);

    int getCastTime();

    void setRigidTime(int rigidTime);

    int getRigidTime();

    void setCoolTime(int coolTime);

    int getCoolTime();

    int getReqLevel();

    List<EquipmentItemCategory> getReqMainHand();

    List<EquipmentItemCategory> getReqOffHand();
}
