package com.somrpg.swordofmagic7.Core.Player.Enum;

public enum DropLog {
    Disable("非表示"),
    Item("アイテムのみ"),
    Rune("ルーンのみ"),
    Both("すべてのドロップ"),
    ;

    private final String display;

    DropLog(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }

    public boolean isItem() {
        return this == Item || isBoth();
    }

    public boolean isRune() {
        return this == Rune || isBoth();
    }

    public boolean isBoth() {
        return this == Both;
    }
}
