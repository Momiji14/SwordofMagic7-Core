package com.somrpg.swordofmagic7.Core.Player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PlayerList {
    private static final Set<Player> playerList = new HashSet<>();

    public static void load() {
        playerList.addAll(Bukkit.getOnlinePlayers());
        offlineCheck();
    }

    public static void addPlayer(Player player) {
        playerList.add(player);
        offlineCheck();
    }

    public static void removePlayer(Player player) {
        playerList.remove(player);
        offlineCheck();
    }

    public static void offlineCheck() {
        playerList.removeIf(player -> !player.isOnline());
    }

    public static Collection<Player> getPlayerList() {
        return playerList;
    }

    public static Collection<Player> getNear(Location loc, double radius) {
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
}
