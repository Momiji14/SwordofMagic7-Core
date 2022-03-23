package com.somrpg.swordofmagic7.Core.Menu;

public enum UserMenuType {
    Categorize("カテゴライズ"),
    ListDisplay("一覧表示"),
    ;

    private final String display;

    UserMenuType(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}
