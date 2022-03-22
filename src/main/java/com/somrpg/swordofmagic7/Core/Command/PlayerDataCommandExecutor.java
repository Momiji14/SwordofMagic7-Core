package com.somrpg.swordofmagic7.Core.Command;

import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

public interface PlayerDataCommandExecutor extends CommandExecutor, PlayerData {
    @Override
    default boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player && player == getPlayer()) {
            command(args);
            return true;
        }
        return false;
    }

    void command(@NonNull String[] args);
}
