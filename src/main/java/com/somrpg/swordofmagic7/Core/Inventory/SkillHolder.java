package com.somrpg.swordofmagic7.Core.Inventory;

public interface SkillHolder {

    static SkillHolder fromDataString(String data) {
        SkillHolder skillHolder = new SkillHolderContainer();
        String[] split = data.split(",");
        skillHolder.setKey(split[0]);
        skillHolder.setType(SkillHolderType.valueOf(split[1]));
        return skillHolder;
    }

    default String toDataString() {
        return getKey() + "," + getType().toString();
    }

    void setKey(String key);

    String getKey();

    SkillHolderType getType();

    void setType(SkillHolderType type);
}
