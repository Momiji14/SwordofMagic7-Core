package com.somrpg.swordofmagic7.Core.Command.DeveloperCommand;

import com.somrpg.swordofmagic7.Core.Command.ConsoleCommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class CommandSomReload implements ConsoleCommandExecutor {
    @Override
    public void command(CommandSender sender, String[] args) {
        Bukkit.getServer().dispatchCommand(sender, "plugman reload swordofmagic7-Core");
    }
}
