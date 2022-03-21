package com.somrpg.swordofmagic7.Core.Player.Enum;

public enum PlayerStrafeMode {
    AirDash("空中ダッシュ"),
    DoubleJump("ダブルジャンプ"),
    Both("すべての条件"),
    ;

    private final String display;

    PlayerStrafeMode(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }

    public boolean isAirDash() {
        return this == AirDash || this == Both;
    }

    public boolean isDoubleJump() {
        return this == DoubleJump || this == Both;
    }
}
