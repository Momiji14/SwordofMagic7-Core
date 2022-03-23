package com.somrpg.swordofmagic7.Core.Player.Interface;

import com.somrpg.swordofmagic7.Core.Menu.AttributeMenuContainer;
import com.somrpg.swordofmagic7.Core.Menu.UserMenuType;
import com.somrpg.swordofmagic7.Core.Player.Container.PlayerOtherContainer;

import java.util.List;
import java.util.Set;

public interface PlayerOther {

    PlayerOtherContainer getPlayerOtherContainer();

    PlayerData getPlayerData();

    default String getNick() {
        return getPlayerOtherContainer().getNick();
    }

    default Set<String> getActiveTeleportGate() {
        return getPlayerOtherContainer().getActiveTeleportGate();
    }
    default void setActiveTeleportGate(Set<String> gate) {
        getPlayerOtherContainer().setActiveTeleportGate(gate);
    }

    default void setAttributePoint(int point) {
        getPlayerOtherContainer().setAttributePoint(point);
    }
    default void addAttributePoint(int point) {
        getPlayerOtherContainer().addAttributePoint(point);
    }
    default int getAttributePoint() {
        return getPlayerOtherContainer().getAttributePoint();
    }
}
