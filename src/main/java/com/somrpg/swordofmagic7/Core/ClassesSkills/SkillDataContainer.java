package com.somrpg.swordofmagic7.Core.ClassesSkills;

import com.somrpg.swordofmagic7.Core.Item.EquipmentItemCategory;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class SkillDataContainer implements SkillData {

    private final String id;
    private final Material material;
    private final String display;
    private final SkillType skillType;
    private final List<String> lore;
    private final List<SkillParameter> skillParameters;
    private int mana;
    private int castTime;
    private int rigidTime;
    private int coolTime;
    private int reqLevel;
    public final List<EquipmentItemCategory> ReqMainHand = new ArrayList<>();
    public final List<EquipmentItemCategory> ReqOffHand = new ArrayList<>();

    SkillDataContainer(String id, Material material, String display, SkillType skillType, List<String> lore, List<SkillParameter> skillParameters) {
        this.id = id;
        this.material = material;
        this.display = display;
        this.skillType = skillType;
        this.lore = lore;
        this.skillParameters = skillParameters;
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
    public List<String> getLore() {
        return lore;
    }

    @Override
    public SkillType getSkillType() {
        return skillType;
    }

    @Override
    public List<SkillParameter> getSkillParameters() {
        return skillParameters;
    }

    @Override
    public double getValue(int i) {
       return getSkillParameters().get(i).getValue();
    }

    @Override
    public int getValueInt(int i) {
        return Math.toIntExact(Math.round(getValue(i)));
    }

    @Override
    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public void setCastTime(int castTime) {
        this.castTime = castTime;
    }

    @Override
    public int getCastTime() {
        return castTime;
    }

    @Override
    public void setRigidTime(int rigidTime) {
        this.rigidTime = rigidTime;
    }

    @Override
    public int getRigidTime() {
        return rigidTime;
    }

    @Override
    public void setCoolTime(int coolTime) {
        this.coolTime = coolTime;
    }

    @Override
    public int getCoolTime() {
        return coolTime;
    }

    @Override
    public void setReqLevel(int reqLevel) {
        this.reqLevel = reqLevel;
    }

    @Override
    public int getReqLevel() {
        return reqLevel;
    }

    @Override
    public List<EquipmentItemCategory> getReqMainHand() {
        return ReqMainHand;
    }

    @Override
    public List<EquipmentItemCategory> getReqOffHand() {
        return ReqOffHand;
    }
}
