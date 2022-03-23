package com.somrpg.swordofmagic7.Core.Player.Interface;

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
}
