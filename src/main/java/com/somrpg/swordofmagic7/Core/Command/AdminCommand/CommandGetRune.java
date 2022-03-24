package com.somrpg.swordofmagic7.Core.Command.AdminCommand;

import com.somrpg.swordofmagic7.Core.Command.PlayerCommandExecutor;
import com.somrpg.swordofmagic7.Core.DataBase.DataBase;
import com.somrpg.swordofmagic7.Core.DataBase.SomItemDataLoader;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Item.RuneItem;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.entity.Player;

public class CommandGetRune implements PlayerCommandExecutor {
    @Override
    public void command(Player player, PlayerData playerData, String[] args) {
        if (args.length >= 1) {
            int level = 1;
            double quality = 0.5;
            if (args.length >= 2) level = Integer.parseInt(args[1]);
            if (args.length >= 3) quality = Double.parseDouble(args[2]);
            SomItemStack item = SomItemDataLoader.getItem(args[0]);
            if (item instanceof RuneItem runeItem) {
                runeItem.setLevel(level);
                runeItem.setQuality(quality);
                playerData.getRuneInventory().addContent(runeItem);
            } else {
                playerData.sendMessage("§c存在しないルーンです", SomSound.Nope);
            }
        }
    }
}
