package com.somrpg.swordofmagic7.Core.Command.BuilderCommand;

import com.somrpg.swordofmagic7.Core.Command.PlayerCommandExecutor;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class CommandPlayMode implements PlayerCommandExecutor {
    @Override
    public void command(Player player, PlayerData playerData, String[] args) {
        boolean pvpMode = !playerData.getPlayerSetting().isPlayMode();
        playerData.getPlayerSetting().setPlayMode(pvpMode);
        playerData.sendMessage("§bPlayMode -> " + pvpMode, SomSound.Tick);
        if (pvpMode) {
            player.setGameMode(GameMode.SURVIVAL);
        } else {
            player.setGameMode(GameMode.CREATIVE);
        }
    }
}
