package com.somrpg.swordofmagic7.Core.Inventory;

public enum SomInventoryType {
    ItemInventory("アイテムインベントリ"),
    RuneInventory("ルーンインベントリ"),
    PetCage("ペットケージ"),
    SkillSlot("スキルスロット"),
    ;

    private final String display;

    SomInventoryType(String display) {
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
        return this == PetCage;
    }

    public boolean isSkillSlot() {
        return this == SkillSlot;
    }
}
