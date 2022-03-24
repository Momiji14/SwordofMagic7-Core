package com.somrpg.swordofmagic7.Core.Player.Interface;

import com.somrpg.swordofmagic7.Core.Player.Container.PlayerMenuContainer;
import com.somrpg.swordofmagic7.Core.Player.Menu.*;
import com.somrpg.swordofmagic7.Core.Player.Production.ShopDisplay;

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
    default ClassMenu getClassMenu() {
        return getPlayerMenuContainer().getClassMenu();
    }
    default SkillSlotMenu getSkillSlotMenu() {
        return getPlayerMenuContainer().getSkillSlotMenu();
    }
    default ShopDisplay getShopDisplay() {
        return getPlayerMenuContainer().getShopDisplay();
    }
}
