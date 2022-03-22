package com.somrpg.swordofmagic7.Core.Command.PlayerCommand;

import com.somrpg.swordofmagic7.Core.Command.PlayerCommandExecutor;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import org.bukkit.entity.Player;

public class CommandCastMode implements PlayerCommandExecutor {
    @Override
    public void command(Player player, PlayerData playerData, String[] args) {
        playerData.getSettingMenu().castMode();
    }
}

