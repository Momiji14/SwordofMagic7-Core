package com.somrpg.swordofmagic7.Core.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager {
    public static void command(CommandSender sender, Command cmd, String[] args) {
        String command = cmd.getName();
        if (sender instanceof Player player) {
            if (command.equalsIgnoreCase("gm")) {
                CommandGameMode.command(player, args);
            }
        }

        if (command.equalsIgnoreCase("somReload")) {
            CommandSomReload.command(sender);
        }
    }
}
