package com.somrpg.swordofmagic7.Core;

import com.somrpg.swordofmagic7.Core.Command.BuilderCommand.CommandGameMode;
import com.somrpg.swordofmagic7.Core.Command.BuilderCommand.CommandPlayMode;
import com.somrpg.swordofmagic7.Core.Command.AdminCommand.CommandGetItem;
import com.somrpg.swordofmagic7.Core.Command.AdminCommand.CommandGetRune;
import com.somrpg.swordofmagic7.Core.Command.DeveloperCommand.CommandLoad;
import com.somrpg.swordofmagic7.Core.Command.DeveloperCommand.CommandSave;
import com.somrpg.swordofmagic7.Core.Command.DeveloperCommand.CommandSomReload;
import com.somrpg.swordofmagic7.Core.DataBase.DataLoader;
import com.somrpg.swordofmagic7.Core.Listener.*;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.PlayerList;
import com.somrpg.swordofmagic7.Core.SomThread.SomTask;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class SomCore extends JavaPlugin {
    private static Plugin plugin;
    private static JavaPlugin javaPlugin;
    private static final SomTask somTask = new SomTask();

    @Override
    public void onEnable() {
        plugin = this;
        javaPlugin = this;

        getServer().getPluginManager().registerEvents(new ConnectionListener(), plugin);
        getServer().getPluginManager().registerEvents(new DamageListener(), plugin);
        getServer().getPluginManager().registerEvents(new InventoryListener(), plugin);
        getServer().getPluginManager().registerEvents(new OthersListener(), plugin);
        getServer().getPluginManager().registerEvents(new PlayerControlListener(), plugin);

        //Developer
        getJavaPlugin().getCommand("somReload").setExecutor(new CommandSomReload());
        getJavaPlugin().getCommand("load").setExecutor(new CommandLoad());
        getJavaPlugin().getCommand("save").setExecutor(new CommandSave());
        //Admin
        getJavaPlugin().getCommand("getItem").setExecutor(new CommandGetItem());
        getJavaPlugin().getCommand("getRune").setExecutor(new CommandGetRune());
        //Builder
        getJavaPlugin().getCommand("gm").setExecutor(new CommandGameMode());
        getJavaPlugin().getCommand("playMode").setExecutor(new CommandPlayMode());

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
