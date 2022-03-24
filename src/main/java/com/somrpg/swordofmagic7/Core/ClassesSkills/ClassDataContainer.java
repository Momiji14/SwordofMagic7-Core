package com.somrpg.swordofmagic7.Core.ClassesSkills;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassDataContainer implements ClassData {

    private final String id;
    private final Material material;
    private final String display;
    private final String nick;
    private final String color;
    private final List<String> lore;
    private final boolean productionClass;
    private final List<SkillData> skillList = new ArrayList<>();
    private final Map<String, Integer> reqClass = new HashMap<>();

    ClassDataContainer(String id, Material material, String display, String color, String nick, List<String> lore, boolean productionClass) {
        this.id = id;
        this.material = material;
        this.display = display;
        this.color = color;
        this.nick = nick;
        this.lore = lore;
        this.productionClass = productionClass;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Material getMaterial() {
        return material;
    }

    @Override
    public String getDisplay() {
        return display;
    }

    @Override
    public String getNick() {
        return nick;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public List<String> getLore() {
        return lore;
    }

    @Override
    public boolean isProductionClass() {
        return productionClass;
    }

    @Override
    public List<SkillData> getSkillList() {
        return skillList;
    }

    @Override
    public Map<String, Integer> getReqClass() {
        return reqClass;
    }
}
