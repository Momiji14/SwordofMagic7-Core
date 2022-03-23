package com.somrpg.swordofmagic7.Core.Player.Container;

import com.somrpg.swordofmagic7.Core.Menu.*;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerMenu;

public class PlayerMenuContainer implements PlayerMenu {

    private final PlayerData playerData;
    private final UserMenu userMenu;
    private final SettingMenu settingMenu;
    private final AttributeMenu attributeMenu;
    private final TeleportGateMenu teleportGateMenu;

    PlayerMenuContainer(PlayerData playerData) {
        this.playerData = playerData;

        userMenu = new UserMenuContainer(playerData);
        attributeMenu = new AttributeMenuContainer(playerData);
        settingMenu = new SettingMenuContainer(playerData);
        teleportGateMenu = new TeleportGateContainer(playerData);
    }

    @Override
    public PlayerMenuContainer getPlayerMenuContainer() {
        return this;
    }

    @Override public UserMenu getUserMenu() {
        return userMenu;
    }
    @Override public AttributeMenu getAttributeMenu() {
        return attributeMenu;
    }
    @Override public SettingMenu getSettingMenu() {
        return settingMenu;
    }
    @Override public TeleportGateMenu getTeleportGateMenu() {
        return teleportGateMenu;
    }
}
