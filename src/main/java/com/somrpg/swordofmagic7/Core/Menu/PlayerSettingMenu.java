package com.somrpg.swordofmagic7.Core.Menu;

import com.somrpg.swordofmagic7.Core.Command.PlayerDataCommandExecutor;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class PlayerSettingMenu implements SettingMenuInterface {

    private final PlayerData playerData;
    public PlayerSettingMenu(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public String getTitle() {
        return "§l設定メニュー";
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }

    @Override
    public void command(String[] args) {
        openGUI();
    }
}
