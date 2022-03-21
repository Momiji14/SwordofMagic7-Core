package com.somrpg.swordofmagic7.Core.Player.Enum;

public enum PlayerCastMode {
    Renewed("リニュード"),
    Legacy("レガシー"),
    Hold("ホールド"),
    ;

    private final String display;

    PlayerCastMode(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }

    public boolean isRenewed() {
        return this == Renewed;
    }

    public boolean isLegacy() {
        return this == Legacy;
    }

    public boolean isHold() {
        return this == Hold;
    }

}
