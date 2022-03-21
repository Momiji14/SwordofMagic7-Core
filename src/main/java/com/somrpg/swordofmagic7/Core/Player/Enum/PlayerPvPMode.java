package com.somrpg.swordofmagic7.Core.Player.Enum;

public enum PlayerPvPMode {
    Hostile("敵対"),
    Friendly("友好"),
    ;

    private final String display;

    PlayerPvPMode(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }

    public boolean isHostile() {
        return this == Hostile;
    }

    public boolean isFriendly() {
        return this == Friendly;
    }
}
