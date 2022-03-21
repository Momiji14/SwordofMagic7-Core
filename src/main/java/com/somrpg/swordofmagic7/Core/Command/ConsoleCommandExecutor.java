package com.somrpg.swordofmagic7.Core.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public interface ConsoleCommandExecutor extends CommandExecutor {

    @Override
    default boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        command(sender, args);
        return true;
    }

    void command(CommandSender sender, String[] args);
}
