package com.somrpg.swordofmagic7.Core.Item;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.StatusParameter;
import org.checkerframework.checker.nullness.qual.NonNull;

public class RuneItem extends SomItemStack {

    private final StatusParameter statusParameter;
    private double quality = 0.5;
    private int level = 0;

    public RuneItem(@NonNull SomItemStack data, @NonNull StatusParameter statusParameter) {
        super(data.getId(), data.getDisplay(), data.getMaterial(), data.getLore());
        this.statusParameter = statusParameter;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

    public double getQuality() {
        return quality;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public StatusParameter getStatus() {
        return statusParameter;
    }
}
