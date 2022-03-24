package com.somrpg.swordofmagic7.Core.Generic.Item;

import org.checkerframework.checker.nullness.qual.NonNull;

public enum ItemCategory {
    Equipment("装備"),
    Tool("ツール"),
    Cook("料理"),
    Potion("ポーション"),
    PetFood("ペットフード"),
    Material("素材"),
    ;

    private final String Display;

    ItemCategory(@NonNull String Display) {
        this.Display = Display;
    }

    public String getDisplay() {
        return Display;
    }

    public boolean isEquipment() {
        return this == Equipment;
    }

    public boolean isTool() {
        return this == Tool;
    }

    public boolean isCook() {
        return this == Cook;
    }

    public boolean isPotion() {
        return this == Potion;
    }

    public boolean isMaterial() {
        return this == Material;
    }

}
