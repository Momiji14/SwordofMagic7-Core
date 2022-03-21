package com.somrpg.swordofmagic7.Core.Player.Interface;

import com.somrpg.swordofmagic7.Core.Menu.PlayerUserMenu;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.entity.Player;

import java.util.List;

public interface PlayerDataInterface {
    PlayerData getPlayerData();

    default Player getPlayer() {
        return getPlayerData().getPlayer();
    }

    default PlayerUserMenu getUserMenu() {
        return getPlayerData().getUserMenu();
    }


    default void sendMessage(String message, SomSound sound) {
        sendMessage(List.of(message.split("\n")), sound);
    }

    default void sendMessage(List<String> message, SomSound sound) {
        Player player = getPlayer();
        for (String msg : message) {
            player.sendMessage(msg);
        }
        sound.play(player);
    }
}
