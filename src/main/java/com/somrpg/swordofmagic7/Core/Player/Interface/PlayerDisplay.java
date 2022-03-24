package com.somrpg.swordofmagic7.Core.Player.Interface;

import com.somrpg.swordofmagic7.Core.Generic.DecoContent;
import com.somrpg.swordofmagic7.Core.SomCore;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.*;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.somrpg.swordofmagic7.Core.Generic.DecoFormat.ScaleDigit;

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
