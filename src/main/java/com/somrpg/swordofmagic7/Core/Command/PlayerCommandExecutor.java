package com.somrpg.swordofmagic7.Core.Command;

import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

public interface PlayerCommandExecutor extends CommandExecutor {
    @Override
    default boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            PlayerData playerData = PlayerData.getData(player);
            command(player, playerData, args);
            return true;
        }
        return false;
    }

    void command(@NonNull Player player, @NonNull PlayerData playerData, @NonNull String[] args);
}
