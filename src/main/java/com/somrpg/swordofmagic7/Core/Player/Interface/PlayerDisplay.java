package com.somrpg.swordofmagic7.Core.Player.Interface;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public interface PlayerDisplay {

    PlayerData getPlayerData();

    default Player getPlayer() {
        return getPlayerData().getPlayer();
    }

    void setSideBar(String key, List<String> data);
    void resetSideBar(String key);
    HashMap<String, List<String>> getSideBar();

    default void startTickUpdate() {
        getPlayerData().getPlayerDisplay().startTickUpdate();
    }

    static void setSideBar(Collection<Player> players, String key, List<String> data) {
        for (Player player : players) {
            if (player.isOnline()) {
                PlayerData.getData(player).getPlayerDisplay().setSideBar(key, data);
            }
        }
    }

    static void resetSideBar(Collection<Player> players, String key) {
        for (Player player : players) {
            if (player.isOnline()) {
                PlayerData.getData(player).getPlayerDisplay().resetSideBar(key);
            }
        }
    }
}
