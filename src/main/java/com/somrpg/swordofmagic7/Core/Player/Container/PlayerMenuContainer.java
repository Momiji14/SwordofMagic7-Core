package com.somrpg.swordofmagic7.Core.Player.Container;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerMenu;
import com.somrpg.swordofmagic7.Core.Player.Menu.*;
import com.somrpg.swordofmagic7.Core.Player.Production.ShopDisplay;
import com.somrpg.swordofmagic7.Core.Player.Production.ShopDisplayContainer;

public class PlayerMenuContainer implements PlayerMenu {

    private final PlayerData playerData;
    private final UserMenu userMenu;
    private final SettingMenu settingMenu;
    private final AttributeMenu attributeMenu;
    private final TeleportGateMenu teleportGateMenu;
    private final ClassMenu classMenu;

    private final ShopDisplay shopDisplay;

    PlayerMenuContainer(PlayerData playerData) {
        this.playerData = playerData;

        userMenu = new UserMenuContainer(playerData);
        attributeMenu = new AttributeMenuContainer(playerData);
        settingMenu = new SettingMenuContainer(playerData);
        teleportGateMenu = new TeleportGateContainer(playerData);
        classMenu = new ClassMenuContainer(playerData);

        shopDisplay = new ShopDisplayContainer(playerData);
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
    @Override public ClassMenu getClassMenu() {
        return classMenu;
    }

    @Override public ShopDisplay getShopDisplay() {
        return shopDisplay;
    }
}
