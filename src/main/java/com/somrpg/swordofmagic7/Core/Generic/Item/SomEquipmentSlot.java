package com.somrpg.swordofmagic7.Core.Generic.Item;

public enum SomEquipmentSlot {
    MainHand("メインハンド", 8),
    OffHand("オフハンド", 40),
    Armor("アーマー", 38),
    ;

    private final String Display;
    private final int slot;

    SomEquipmentSlot(String Display, int slot) {
        this.Display = Display;
        this.slot = slot;
    }

    public String getDisplay() {
        return Display;
    }

    public int getSlot() {
        return slot;
    }
}
