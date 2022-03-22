package com.somrpg.swordofmagic7.Core.Generic.ItemStack;

import org.checkerframework.checker.nullness.qual.NonNull;

public class ItemOwner {

    private String owner = "GameSystem";

    public ItemOwner() {}

    public ItemOwner(@NonNull String owner) {
        this.owner = owner;
    }

    public void setOwner(@NonNull String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }
}
