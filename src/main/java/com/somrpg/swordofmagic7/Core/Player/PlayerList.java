package com.somrpg.swordofmagic7.Core.Player;

import com.somrpg.swordofmagic7.Core.SomCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public interface PlayerList {
    Set<Player> playerList = new HashSet<>();

    static void load() {
        playerList.addAll(Bukkit.getOnlinePlayers());
        offlineCheck();
    }

    static void addPlayer(Player player) {
        playerList.add(player);
        offlineCheck();
    }

    static void removePlayer(Player player) {
        playerList.remove(player);
        offlineCheck();
    }

    static void offlineCheck() {
        SomCore.getSomTask().AsyncTaskLater(() -> playerList.removeIf(player -> !player.isOnline()), 5);
    }

    static Collection<Player> getPlayerList() {
        return playerList;
    }

    static Collection<Player> getNear(Location loc, double radius) {
        Set<Player> list = new HashSet<>();
        try {
            for (Player player : playerList) {
                if (player.isOnline()) {
                    if (player.getLocation().distance(loc) <= radius) {
                        list.add(player);
                    }
                }
            }
            return list;
        } catch (Exception e) {
            return list;
        }
    }

    static boolean isInPlayer(Location location, double radius) {
        for (Player player : playerList) {
            if (player.isOnline()) {
                if (player.getLocation().distance(location) <= radius) {
                    return true;
                }
            }
        }
        return false;
    }
}
