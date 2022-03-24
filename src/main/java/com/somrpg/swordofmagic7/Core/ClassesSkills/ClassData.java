package com.somrpg.swordofmagic7.Core.ClassesSkills;

import org.bukkit.Material;

import java.util.List;
import java.util.Map;

public interface ClassData {
    String getId();

    Material getMaterial();

    String getDisplay();

    String getNick();

    String getColor();

    List<String> getLore();

    boolean isProductionClass();

    List<SkillData> getSkillList();

    Map<String, Integer> getReqClass();
}
