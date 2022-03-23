package com.somrpg.swordofmagic7.Core.Command;

import com.somrpg.swordofmagic7.Core.Command.AdminCommand.CommandGetItem;
import com.somrpg.swordofmagic7.Core.Command.AdminCommand.CommandGetRune;
import com.somrpg.swordofmagic7.Core.Command.BuilderCommand.CommandGameMode;
import com.somrpg.swordofmagic7.Core.Command.BuilderCommand.CommandPlayMode;
import com.somrpg.swordofmagic7.Core.Command.DeveloperCommand.CommandLoad;
import com.somrpg.swordofmagic7.Core.Command.DeveloperCommand.CommandSave;
import com.somrpg.swordofmagic7.Core.Command.DeveloperCommand.CommandSomReload;
import com.somrpg.swordofmagic7.Core.Command.PlayerCommand.*;
import com.somrpg.swordofmagic7.Core.SomCore;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandRegisterExecutor {

    public static void registerExecutors() {
        Map<String, CommandExecutor> executors = new HashMap<>();

        //Developer
        executors.put("somReload", new CommandSomReload());
        executors.put("load", new CommandLoad());
        executors.put("save", new CommandSave());
        //Admin
        executors.put("getItem", new CommandGetItem());
        executors.put("getRune", new CommandGetRune());
        //Builder
        executors.put("gm", new CommandGameMode());
        executors.put("playMode", new CommandPlayMode());
        //Player
        executors.put("menu", new CommandUserMenu());
        executors.put("settingMenu", new CommandSettingMenu());
        executors.put("damageLog", new CommandDamageLog());
        executors.put("expLog", new CommandExpLog());
        executors.put("dropLog", new CommandDropLog());
        executors.put("pvpMode", new CommandPvPMode());
        executors.put("castMode", new CommandCastMode());
        executors.put("strafeMode", new CommandStrafeMode());
        executors.put("userMenuType", new CommandStrafeMode());
        
        for (Map.Entry<String, CommandExecutor> executor : executors.entrySet()) {
            try {
                PluginCommand command = SomCore.getJavaPlugin().getCommand(executor.getKey());
                assert command != null;
                command.setExecutor(executor.getValue());
            } catch (Exception e) {
                SomCore.log("§cRegisterCommandExecutorError -> " + executor.getKey());
            }
        }
    }
}
