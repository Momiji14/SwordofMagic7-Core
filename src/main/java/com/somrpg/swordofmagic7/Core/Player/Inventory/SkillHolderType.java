package com.somrpg.swordofmagic7.Core.Player.Inventory;

public enum SkillHolderType {
    None,
    Item,
    Skill,
    ;

    public boolean isNone() {
        return this == None;
    }

    public boolean isItem() {
        return this == Item;
    }

    public boolean isSkill() {
        return this == Skill;
    }

}
