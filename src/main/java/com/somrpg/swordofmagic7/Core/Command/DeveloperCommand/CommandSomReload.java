package com.somrpg.swordofmagic7.Core.Command.DeveloperCommand;

import com.somrpg.swordofmagic7.Core.Command.ConsoleCommandExecutor;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.PlayerList;
import com.somrpg.swordofmagic7.Core.SomCore;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSomReload implements ConsoleCommandExecutor {
    @Override
    public void command(CommandSender sender, String[] args) {
        for (Player player : PlayerList.getPlayerList()) {
            PlayerData.getData(player).save();
        }
        SomCore.getSomTask().SyncTaskLater(() -> Bukkit.getServer().dispatchCommand(sender, "plugman reload swordofmagic7-Core"), 5);
    }
}
