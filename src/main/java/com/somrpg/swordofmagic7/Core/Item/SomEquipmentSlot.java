package com.somrpg.swordofmagic7.Core.Item;

public enum SomEquipmentSlot {
    MainHand("メインハンド"),
    OffHand("オフハンド"),
    Armor("アーマー"),
    ;

    private final String Display;

    SomEquipmentSlot(String Display) {
        this.Display = Display;
    }

    public String getDisplay() {
        return Display;
    }
}
