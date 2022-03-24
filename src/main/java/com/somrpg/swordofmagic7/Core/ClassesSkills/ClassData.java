package com.somrpg.swordofmagic7.Core.ClassesSkills;

import org.bukkit.Material;

import java.util.List;
import java.util.Map;

public interface ClassData {

    static ClassData create(String id, Material material, String display, String nick, String color, List<String> lore, boolean productionClass) {
        return new ClassDataContainer(id, material, display, nick, color, lore, productionClass);
    }

    String getId();

    Material getMaterial();

    String getDisplay();

    String getNick();

    String getColor();

    List<String> getLore();

    boolean isProductionClass();

    List<SkillData> getSkillList();

    Map<ClassData, Integer> getReqClass();
}
