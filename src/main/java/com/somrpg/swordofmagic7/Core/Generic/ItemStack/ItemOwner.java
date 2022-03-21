package com.somrpg.swordofmagic7.Core.Generic.ItemStack;

public class ItemOwner {

    private String owner = "GameSystem";

    public ItemOwner() {}

    public ItemOwner(String owner) {
        this.owner = owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }
}
