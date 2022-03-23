package com.somrpg.swordofmagic7.Core.Player.Container;

import com.somrpg.swordofmagic7.Core.Menu.AttributeMenuContainer;
import com.somrpg.swordofmagic7.Core.Menu.UserMenuType;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerOther;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerOtherContainer implements PlayerOther {

    private final PlayerData playerData;
    private Set<String> activeTeleportGate = new HashSet<>();
    private int AttributePoint = 0;

    PlayerOtherContainer(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public PlayerOtherContainer getPlayerOtherContainer() {
        return this;
    }

    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }

    @Override
    public String getNick() {
        return null;
    }

    @Override public Set<String> getActiveTeleportGate() {
        return activeTeleportGate;
    }
    @Override public void setActiveTeleportGate(Set<String> gate) {
        activeTeleportGate = gate;
    }

    @Override public void setAttributePoint(int point) {
        AttributePoint = point;
    }
    @Override public void addAttributePoint(int point) {
        AttributePoint += point;
    }
    @Override public int getAttributePoint() {
        return AttributePoint;
    }
}
