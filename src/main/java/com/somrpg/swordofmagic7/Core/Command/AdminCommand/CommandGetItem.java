package com.somrpg.swordofmagic7.Core.Command.AdminCommand;

import com.somrpg.swordofmagic7.Core.Command.PlayerCommandExecutor;
import com.somrpg.swordofmagic7.Core.DataBase.SomItemDataLoader;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Generic.Item.BaseItem;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.entity.Player;

public class CommandGetItem implements PlayerCommandExecutor {
    @Override
    public void command(Player player, PlayerData playerData, String[] args) {
        if (args.length >= 1) {
            int amount = 1;
            if (args.length >= 2) amount = Integer.parseInt(args[1]);
            SomItemStack item = SomItemDataLoader.getItem(args[0]);
            if (item instanceof BaseItem baseItem) {
                baseItem.setAmount(amount);
                playerData.getItemInventory().addContent(baseItem);
            } else {
                playerData.sendMessage("§c存在しないアイテムです", SomSound.Nope);
            }
        }
    }
}
