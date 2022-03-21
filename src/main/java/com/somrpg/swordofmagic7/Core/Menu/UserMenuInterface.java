package com.somrpg.swordofmagic7.Core.Menu;

import com.somrpg.swordofmagic7.Core.Generic.BaseGraphicalUserInterface;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.ViewableItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public interface UserMenuInterface extends BaseGraphicalUserInterface {
    String display = "§lユーザーメニュー";
    int size = 2;

    ItemStack ItemInventoryIcon = ViewableItemStack.create("アイテムインベントリ",Material.CHEST, "インベントリ表示をアイテムインベントリに切り替えます", 0).viewItemStack();

    @Override
    default ItemStack[] getContent() {
        ItemStack[] content = new ItemStack[size*9];
        content[0] = ItemInventoryIcon;
        return content;
    }
}
