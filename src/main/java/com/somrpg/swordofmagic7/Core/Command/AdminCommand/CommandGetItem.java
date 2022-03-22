package com.somrpg.swordofmagic7.Core.Command.AdminCommand;

import com.somrpg.swordofmagic7.Core.Command.PlayerCommandExecutor;
import com.somrpg.swordofmagic7.Core.DataBase.DataBase;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Item.BaseItem;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

public class CommandGetItem implements PlayerCommandExecutor {
    @Override
    public void command(@NotNull Player player, @NotNull @NonNull PlayerData playerData, String[] args) {
        if (args.length >= 1) {
            int amount = 1;
            if (args.length >= 2) amount = Integer.parseInt(args[1]);
            SomItemStack item = DataBase.getSomItemStack(args[0]);
            if (item instanceof BaseItem baseItem) {
                baseItem.setAmount(amount);
                playerData.getItemInventory().addContent(baseItem);
            } else {
                playerData.sendMessage("§c存在しないアイテムです", SomSound.Nope);
            }
        }
    }
}
