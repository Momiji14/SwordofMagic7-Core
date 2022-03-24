package com.somrpg.swordofmagic7.Core.Player.Container;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerOther;

import java.util.HashSet;
import java.util.Set;

public class PlayerOtherContainer implements PlayerOther {

    private final PlayerData playerData;
    private Set<String> activeTeleportGate = new HashSet<>();
    private int AttributePoint = 0;
    private String nick;

    PlayerOtherContainer(PlayerData playerData) {
        this.playerData = playerData;
        setNick(playerData.getPlayer().getName());
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
    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public String getNick() {
        return nick;
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
