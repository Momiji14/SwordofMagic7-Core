package com.somrpg.swordofmagic7.Core.Player.Enum;

public enum ExpLog {
    Disable("非表示"),
    Player("プレイヤー経験値"),
    Class("クラス経験値"),
    Both("すべての経験値"),
    ;

    private final String display;

    ExpLog(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }

    public boolean isPlayer() {
        return this == Player || isBoth();
    }

    public boolean isClass() {
        return this == Class || isBoth();
    }

    public boolean isBoth() {
        return this == Both;
    }
}
