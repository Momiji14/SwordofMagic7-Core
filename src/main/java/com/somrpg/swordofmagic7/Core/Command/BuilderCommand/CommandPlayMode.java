package com.somrpg.swordofmagic7.Core.Command.BuilderCommand;

import com.somrpg.swordofmagic7.Core.Command.PlayerCommandExecutor;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.entity.Player;

public class CommandPlayMode implements PlayerCommandExecutor {
    @Override
    public void command(Player player, PlayerData playerData, String[] args) {
        playerData.getPlayerSetting().setPlayMode(!playerData.getPlayerSetting().isPlayMode());
        playerData.sendMessage("§bPlayMode -> " + playerData.getPlayerSetting().isPlayMode(), SomSound.Tick);
    }
}
