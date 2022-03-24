package com.somrpg.swordofmagic7.Core.ClassesSkills;

import static com.somrpg.swordofmagic7.Core.Generic.DecoFormat.ScaleDigit;

public class SkillParameterContainer implements SkillParameter {

    private final String display;
    private final double value;
    private final double increase;
    private final String prefix;
    private final String suffix;
    private final int digit;

    SkillParameterContainer(String display, double value, double increase, String prefix, String suffix, int digit) {
        this.display = display;
        this.value = value;
        this.increase = increase;
        this.prefix = prefix;
        this.suffix = suffix;
        this.digit = digit;
    }

    @Override
    public String getDisplay() {
        return display;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public double getIncrease() {
        return increase;
    }

    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public String getSuffix() {
        return suffix;
    }

    @Override
    public int getDigit() {
        return digit;
    }

    @Override
    public String valueView() {
        if (digit > -1) {
            return prefix + ScaleDigit(value, digit) + suffix;
        } else {
            return prefix + suffix;
        }
    }
}
