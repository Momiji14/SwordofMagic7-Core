package com.somrpg.swordofmagic7.Core.Command;

import com.somrpg.swordofmagic7.Core.DataBase.SomLoader;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Item.BaseItem;
import com.somrpg.swordofmagic7.Core.Item.RuneItem;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager {
    public static void command(CommandSender sender, Command cmd, String[] args) {
        try {
            String command = cmd.getName();
            if (sender instanceof Player player) {
                PlayerData playerData = PlayerData.getData(player);
                if (command.equalsIgnoreCase("gm")) {
                    CommandGameMode.command(player, args);
                }

                if (args.length >= 1) {
                    if (command.equalsIgnoreCase("getItem")) {
                        int amount = 1;
                        if (args.length >= 2) amount = Integer.parseInt(args[1]);
                        SomItemStack item = SomLoader.getSomItemStack(args[0]);
                        if (item instanceof BaseItem baseItem) {
                            baseItem.setAmount(amount);
                            playerData.getItemInventory().addContent(baseItem);
                        } else {
                            playerData.sendMessage("§c存在しないアイテムです", SomSound.Nope);
                        }
                    } else if (command.equalsIgnoreCase("getRune")) {
                        int level = 1;
                        double quality = 0.5;
                        if (args.length >= 2) level = Integer.parseInt(args[1]);
                        if (args.length >= 3) quality = Double.parseDouble(args[2]);
                        SomItemStack item = SomLoader.getSomItemStack(args[0]);
                        if (item instanceof RuneItem runeItem) {
                            runeItem.setLevel(level);
                            runeItem.setQuality(quality);
                            playerData.getItemInventory().addContent(runeItem);
                        } else {
                            playerData.sendMessage("§c存在しないルーンです", SomSound.Nope);
                        }
                    }
                }
            }

            if (command.equalsIgnoreCase("somReload")) {
                CommandSomReload.command(sender);
            }
        } catch (Exception e) {
            sender.sendMessage("§cコマンドの実行に失敗しました -> " + e.getMessage());
        }
    }
}
