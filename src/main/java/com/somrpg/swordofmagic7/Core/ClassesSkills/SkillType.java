package com.somrpg.swordofmagic7.Core.ClassesSkills;

public enum SkillType {
    Active("アクティブ"),
    Passive("パッシブ"),
    ;

    private final String Display;

    SkillType(String Display) {
        this.Display = Display;
    }

    public String getDisplay() {
        return Display;
    }

    public boolean isActive() {
        return this == Active;
    }

    public boolean isPassive() {
        return this == Passive;
    }
}
