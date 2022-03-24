package com.somrpg.swordofmagic7.Core;

import com.somrpg.swordofmagic7.Core.Command.CommandRegisterExecutor;
import com.somrpg.swordofmagic7.Core.DataBase.DataBase;
import com.somrpg.swordofmagic7.Core.Listener.*;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.PlayerList;
import com.somrpg.swordofmagic7.Core.SomThread.SomTask;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class SomCore extends JavaPlugin {
    private static Plugin plugin;
    private static JavaPlugin javaPlugin;
    private static final SomTask somTask = new SomTask();
    private static final Random random = new Random();

    @Override
    public void onEnable() {
        plugin = this;
        javaPlugin = this;

        Bukkit.getPluginManager().registerEvents(new ConnectionListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new DamageListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new OthersListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerControlListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new PhysicsListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new BlockListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new InteractListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerControlListener(), plugin);

        CommandRegisterExecutor.registerExecutors();
        PlayerList.load();
        DataBase.dataLoadable();
        DataBase.singleLoad();

        for (Player player : PlayerList.getPlayerList()) {
            PlayerData.getData(player).load();
        }
    }

    @Override
    public void onDisable() {

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

    public static Random getRandom() {
        return random;
    }
}
