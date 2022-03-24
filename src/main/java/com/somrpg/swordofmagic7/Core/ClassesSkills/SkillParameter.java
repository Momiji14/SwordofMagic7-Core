package com.somrpg.swordofmagic7.Core.ClassesSkills;

public interface SkillParameter {

    static SkillParameter create(String display, double value, double increase, String prefix, String suffix, int digit) {
        return new SkillParameterContainer(display, value, increase, prefix, suffix, digit);
    }

    String getDisplay();

    double getValue();

    double getIncrease();

    String getPrefix();

    String getSuffix();

    int getDigit();

    String valueView();
}
