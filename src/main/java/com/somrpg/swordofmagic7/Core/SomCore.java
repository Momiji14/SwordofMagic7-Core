package com.somrpg.swordofmagic7.Core;

import com.somrpg.swordofmagic7.Core.Command.CommandManager;
import com.somrpg.swordofmagic7.Core.DataBase.DataLoader;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.PlayerList;
import com.somrpg.swordofmagic7.Core.SomThread.SomTask;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class SomCore extends JavaPlugin {
    private static Plugin plugin;
    private static JavaPlugin javaPlugin;
    private static final SomTask somTask = new SomTask();

    @Override
    public void onEnable() {
        plugin = this;
        javaPlugin = this;

        getServer().getPluginManager().registerEvents(new Events(), plugin);

        PlayerList.load();
        DataLoader.startLoad();

        for (Player player : PlayerList.getPlayerList()) {
            PlayerData.getData(player).load();
        }
    }

    @Override
    public void onDisable() {
        for (Player player : PlayerList.getPlayerList()) {
            PlayerData.getData(player).save();
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        try {
            CommandManager.command(sender, cmd, args);
            return true;
        } catch (Exception e) {
            sender.sendMessage(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public static Plugin getPlugin() {
        return plugin;
    }
    public static JavaPlugin getJavaPlugin() {
        return javaPlugin;
    }

    public static SomTask getSomTask() {
        return somTask;
    }

    public static void log(String log) {
        for (Player player : PlayerList.getPlayerList()) {
            if (player.hasPermission("som7.log")) {
                player.sendMessage(log);
            }
        }
    }
}
