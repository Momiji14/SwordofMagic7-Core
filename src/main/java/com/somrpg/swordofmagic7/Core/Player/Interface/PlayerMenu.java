package com.somrpg.swordofmagic7.Core.Player.Interface;

import com.somrpg.swordofmagic7.Core.Menu.AttributeMenu;
import com.somrpg.swordofmagic7.Core.Menu.SettingMenu;
import com.somrpg.swordofmagic7.Core.Menu.TeleportGateMenu;
import com.somrpg.swordofmagic7.Core.Menu.UserMenu;
import com.somrpg.swordofmagic7.Core.Player.Container.PlayerMenuContainer;

public interface PlayerMenu {

    PlayerMenuContainer getPlayerMenuContainer();

    default UserMenu getUserMenu() {
        return getPlayerMenuContainer().getUserMenu();
    }
    default AttributeMenu getAttributeMenu() {
        return getPlayerMenuContainer().getAttributeMenu();
    }
    default SettingMenu getSettingMenu() {
        return getPlayerMenuContainer().getSettingMenu();
    }
    default TeleportGateMenu getTeleportGateMenu() {
        return getPlayerMenuContainer().getTeleportGateMenu();
    }

}
