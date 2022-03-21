package com.somrpg.swordofmagic7.Core.Player.Enum;

public enum DamageLog {
    Disable("非表示"),
    DamageOnly("ダメージのみ"),
    Detail("詳細情報"),
    Debug("デバッグ"),
    ;

    private final String display;

    DamageLog(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }

    public boolean isDamageOnly() {
        return this == DamageOnly || isDetail() || isDebug();
    }

    public boolean isDetail() {
        return this == Detail || isDebug();
    }

    public boolean isDebug() {
        return this == Debug;
    }
}
