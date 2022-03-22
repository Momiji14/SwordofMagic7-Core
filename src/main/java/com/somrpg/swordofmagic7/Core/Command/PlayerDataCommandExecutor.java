package com.somrpg.swordofmagic7.Core.Command;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface PlayerDataCommandExecutor extends CommandExecutor {

    PlayerData getPlayerData();

    @Override
    default boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player player && player == getPlayerData().getPlayer()) {
            command(args);
            return true;
        }
        return false;
    }

    void command(String[] args);
}
