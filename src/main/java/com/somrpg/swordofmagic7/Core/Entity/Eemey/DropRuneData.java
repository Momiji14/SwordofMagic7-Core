package com.somrpg.swordofmagic7.Core.Entity.Eemey;

import com.somrpg.swordofmagic7.Core.Generic.Item.RuneItem;

public interface DropRuneData {
    RuneItem getRuneItem();

    double getPercent();

    int getMaxLevel();

    int getMinLevel();
}
