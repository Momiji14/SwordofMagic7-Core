package com.somrpg.swordofmagic7.Core.Command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class CommandSomReload {
    public static void command(CommandSender sender) {
        Bukkit.getServer().dispatchCommand(sender, "plugman reload swordofmagic7-Core");
    }
}
