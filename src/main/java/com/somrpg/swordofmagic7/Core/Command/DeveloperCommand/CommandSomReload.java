package com.somrpg.swordofmagic7.Core.Command.DeveloperCommand;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.somrpg.swordofmagic7.Core.Command.ConsoleCommandExecutor;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.PlayerList;
import com.somrpg.swordofmagic7.Core.SomCore;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandSomReload implements ConsoleCommandExecutor {
    @Override
    public void command(@NotNull CommandSender sender, String[] args) {
        for (Player player : PlayerList.getPlayerList()) {
            PlayerData.getData(player).save();
        }
        for (Hologram hologram : HologramsAPI.getHolograms(SomCore.getPlugin())) {
            hologram.delete();
        }
        SomCore.getSomTask().SyncTaskLater(() -> Bukkit.getServer().dispatchCommand(sender, "plugman reload swordofmagic7-Core"), 5);
    }
}
