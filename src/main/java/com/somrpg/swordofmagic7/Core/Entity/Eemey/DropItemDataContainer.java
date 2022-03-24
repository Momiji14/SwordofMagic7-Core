package com.somrpg.swordofmagic7.Core.Entity.Eemey;

import com.somrpg.swordofmagic7.Core.Generic.Item.BaseItem;

public class DropItemDataContainer implements DropItemData {
    private final BaseItem item;
    private final int maxAmount;
    private final int minAmount;
    private final double percent;
    private final int maxLevel;
    private final int minLevel;

    DropItemDataContainer(BaseItem item, double percent, int maxAmount, int minAmount, int maxLevel, int minLevel) {
        this.item = item;
        this.percent = percent;
        this.maxAmount = maxAmount;
        this.minAmount = minAmount;
        this.maxLevel = maxLevel;
        this.minLevel = minLevel;
    }

    @Override
    public BaseItem getItem() {
        return item;
    }

    @Override
    public double getPercent() {
        return percent;
    }

    @Override
    public int getMaxAmount() {
        return maxAmount;
    }

    @Override
    public int getMinAmount() {
        return minAmount;
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
