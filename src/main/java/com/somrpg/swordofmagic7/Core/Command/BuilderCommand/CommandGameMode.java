package com.somrpg.swordofmagic7.Core.Command.BuilderCommand;

import com.somrpg.swordofmagic7.Core.Command.PlayerCommandExecutor;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class CommandGameMode implements PlayerCommandExecutor {
    @Override
    public void command(Player player, PlayerData playerData, String[] args) {
        if (args.length == 1) {
            GameMode gameMode;
            int gm = Integer.parseInt(args[0]);
            switch (gm) {
                case 1 -> gameMode = GameMode.CREATIVE;
                case 2 -> gameMode = GameMode.ADVENTURE;
                case 3 -> gameMode = GameMode.SPECTATOR;
                default -> gameMode = GameMode.SURVIVAL;
            }
            player.setGameMode(gameMode);
        }
    }
}
