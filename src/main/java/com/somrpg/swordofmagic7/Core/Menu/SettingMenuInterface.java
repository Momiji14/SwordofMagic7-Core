package com.somrpg.swordofmagic7.Core.Menu;

import com.somrpg.swordofmagic7.Core.Generic.BaseGraphicalUserInterface;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.ViewableItemStack;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.inventory.ItemStack;

public interface SettingMenuInterface extends BaseGraphicalUserInterface, CommandExecutor {
    String display = "§l設定メニュー";
    int size = 1;

    ItemStack DamageLogIcon = ViewableItemStack.create("ダメージログ", Material.IRON_SWORD, "ダメージログ表示を切り替えます").viewItemStack();
    ItemStack ExpLogIcon = ViewableItemStack.create("経験値ログ", Material.IRON_SWORD, "経験値ログ表示を切り替えます").viewItemStack();
    ItemStack DropLogIcon = ViewableItemStack.create("ドロップログ", Material.IRON_SWORD, "ドロップログ表示を切り替えます").viewItemStack();
    ItemStack PvPModeIcon = ViewableItemStack.create("PvP設定", Material.IRON_SWORD, "PvP設定を切り替えます").viewItemStack();

    @Override
    default ItemStack[] getContent() {
        ItemStack[] content = new ItemStack[size*9];
        content[0] = DamageLogIcon;
        content[1] = ExpLogIcon;
        content[2] = DropLogIcon;
        content[3] = PvPModeIcon;
        return content;
    }

    default void onClick(ItemStack currentItem) {
        if (currentItem.equals(DamageLogIcon)) {
            getPlayerData().getPlayerSetting();
        }
    }
}
