package com.somrpg.swordofmagic7.Core.Generic.Item;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.GenericStatus;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.GenericStatusContainer;
import org.checkerframework.checker.nullness.qual.NonNull;

public class RuneItem extends SomItemStack implements Cloneable {

    private final GenericStatus genericStatus;
    private double quality = 0.5;
    private int level = 0;

    public RuneItem(@NonNull SomItemStack data, @NonNull GenericStatusContainer statusParameterContainer) {
        super(data.getId(), data.getDisplay(), data.getMaterial(), data.getLore());
        this.genericStatus = statusParameterContainer;
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

    public GenericStatus getStatusParameter() {
        return genericStatus;
    }

    public RuneItem cloneRune() {
        return clone();
    }

    @Override
    public RuneItem clone() {
        RuneItem clone = (RuneItem) super.clone();
        // TODO: このクローンが元の内部を変更できないようにミュータブルな状態をここにコピーします
        return clone;
    }
}
