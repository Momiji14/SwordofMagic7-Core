package com.somrpg.swordofmagic7.Core.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

public interface ConsoleCommandExecutor extends CommandExecutor {

    @Override
    default boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        command(sender, args);
        return true;
    }

    void command(@NonNull CommandSender sender, @NonNull String[] args);
}
