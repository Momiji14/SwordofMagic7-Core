package com.somrpg.swordofmagic7.Core.Inventory;

public class SkillHolderContainer implements SkillHolder {
    private String key;
    private SkillHolderType type = SkillHolderType.None;

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public SkillHolderType getType() {
        return type;
    }

    @Override
    public void setType(SkillHolderType type) {
        this.type = type;
    }
}
