package com.somrpg.swordofmagic7.Core.Command.DeveloperCommand;

import com.somrpg.swordofmagic7.Core.Command.ConsoleCommandExecutor;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSave implements ConsoleCommandExecutor {
    @Override
    public void command(CommandSender sender, String[] args) {
        if (args.length == 1) {
            Player player = Bukkit.getPlayer(args[0]);
            if (player != null && player.isOnline()) {
                PlayerData.getData(player).save();
                sender.sendMessage(player.getName() + "'s data save");
            }
        } else if (sender instanceof Player player) {
            PlayerData.getData(player).save();
        }
    }
}
