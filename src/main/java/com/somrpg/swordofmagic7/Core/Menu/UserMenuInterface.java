package com.somrpg.swordofmagic7.Core.Menu;

import com.somrpg.swordofmagic7.Core.Command.PlayerDataCommandExecutor;
import com.somrpg.swordofmagic7.Core.Generic.BaseGraphicalUserInterface;
import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.ViewableItemStack;
import com.somrpg.swordofmagic7.Core.Inventory.SomInventoryType;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.inventory.ItemStack;

public interface UserMenuInterface extends BaseGraphicalUserInterface, PlayerDataCommandExecutor {
    String display = "§lユーザーメニュー";
    int size = 3;

    ItemStack ItemInventoryIcon = ViewableItemStack.create(SomInventoryType.ItemInventory.getDisplay(),Material.CHEST, "インベントリ表示をアイテムに切り替えます").viewItemStack();
    ItemStack RuneInventoryIcon = ViewableItemStack.create(SomInventoryType.RuneInventory.getDisplay(),Material.ENDER_CHEST, "インベントリ表示をルーンに切り替えます").viewItemStack();
    ItemStack PetInventoryIcon = ViewableItemStack.create(SomInventoryType.PetInventory.getDisplay(),Material.NOTE_BLOCK, "インベントリ表示をペットケージに切り替えます").viewItemStack();
    ItemStack SkillSlotIcon = ViewableItemStack.create(SomInventoryType.SkillSlot.getDisplay(),Material.END_CRYSTAL, "インベントリ表示をスキルスロットに切り替えます").viewItemStack();

    @Override
    default ItemStack[] getContent() {
        ItemStack[] content = new ItemStack[size*9];
        content[0] = ItemInventoryIcon;
        content[1] = RuneInventoryIcon;
        content[2] = PetInventoryIcon;
        content[3] = SkillSlotIcon;

        for (int i = 9; i < 18; i++) {
            content[i] = GenericConfig.GUIPartition;
        }
        return content;
    }

    default void onClick(ItemStack currentItem) {
        if (currentItem.equals(ItemInventoryIcon)) {
            getPlayerData().getPlayerSetting().setViewInventory(SomInventoryType.ItemInventory);
        } else if (currentItem.equals(RuneInventoryIcon)) {
            getPlayerData().getPlayerSetting().setViewInventory(SomInventoryType.RuneInventory);
        } else if (currentItem.equals(PetInventoryIcon)) {
            getPlayerData().getPlayerSetting().setViewInventory(SomInventoryType.PetInventory);
        } else if (currentItem.equals(SkillSlotIcon)) {
            getPlayerData().getPlayerSetting().setViewInventory(SomInventoryType.SkillSlot);
        }
    }
}
