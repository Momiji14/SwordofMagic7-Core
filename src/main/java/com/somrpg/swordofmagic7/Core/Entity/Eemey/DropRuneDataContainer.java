package com.somrpg.swordofmagic7.Core.Entity.Eemey;

import com.somrpg.swordofmagic7.Core.Generic.Item.RuneItem;

public class DropRuneDataContainer implements DropRuneData {

    private final RuneItem runeItem;
    private final double percent;
    private final int maxLevel;
    private final int minLevel;

    DropRuneDataContainer(RuneItem runeItem, double percent, int maxLevel, int minLevel) {
        this.runeItem = runeItem;
        this.percent = percent;
        this.maxLevel = maxLevel;
        this.minLevel = minLevel;
    }

    @Override
    public RuneItem getRuneItem() {
        return runeItem;
    }

    @Override
    public double getPercent() {
        return percent;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    @Override
    public int getMinLevel() {
        return minLevel;
    }
}
