package com.somrpg.swordofmagic7.Core.Entity.Eemey;

import com.somrpg.swordofmagic7.Core.Generic.Item.BaseItem;

public interface DropItemData {
    BaseItem getItem();

    double getPercent();

    int getMaxAmount();

    int getMinAmount();

    int getMaxLevel();

    int getMinLevel();
}
