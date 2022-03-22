package com.somrpg.swordofmagic7.Core.Inventory;

import org.checkerframework.checker.nullness.qual.NonNull;

public enum SomInventoryType {
    ItemInventory("アイテムインベントリ"),
    RuneInventory("ルーンインベントリ"),
    PetInventory("ペットケージ"),
    SkillSlot("スキルスロット"),
    ;

    private final String display;

    SomInventoryType(@NonNull String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }

    public boolean isItemInventory() {
        return this == ItemInventory;
    }

    public boolean isRuneInventory() {
        return this == RuneInventory;
    }

    public boolean isPetCage() {
        return this == PetInventory;
    }

    public boolean isSkillSlot() {
        return this == SkillSlot;
    }
}
