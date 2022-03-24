package com.somrpg.swordofmagic7.Core.ClassesSkills;

import com.somrpg.swordofmagic7.Core.Item.EquipmentItemCategory;
import org.bukkit.Material;

import java.util.List;

public interface SkillData {

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

    void setCastTime(int castTime);

    int getCastTime();

    void setRigidTime(int rigidTime);

    int getRigidTime();

    void setCoolTime(int coolTime);

    int getCoolTime();

    void setReqLevel(int reqLevel);

    int getReqLevel();

    List<EquipmentItemCategory> getReqMainHand();

    List<EquipmentItemCategory> getReqOffHand();
}
