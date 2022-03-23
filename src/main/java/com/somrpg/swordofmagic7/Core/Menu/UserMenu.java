package com.somrpg.swordofmagic7.Core.Menu;

import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.ViewableItemStack;
import com.somrpg.swordofmagic7.Core.Inventory.SomInventoryType;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface UserMenu extends BaseMenu {
    ItemStack ItemInventoryIcon = ViewableItemStack.create(SomInventoryType.ItemInventory.getDisplay(),Material.CHEST, "インベントリ表示をアイテムに切り替えます").viewItemStack();
    ItemStack RuneInventoryIcon = ViewableItemStack.create(SomInventoryType.RuneInventory.getDisplay(),Material.ENDER_CHEST, "インベントリ表示をルーンに切り替えます").viewItemStack();
    ItemStack PetInventoryIcon = ViewableItemStack.create(SomInventoryType.PetInventory.getDisplay(),Material.NOTE_BLOCK, "インベントリ表示をペットケージに切り替えます").viewItemStack();
    ItemStack SkillSlotIcon = ViewableItemStack.create(SomInventoryType.SkillSlot.getDisplay(),Material.END_CRYSTAL, "インベントリ表示をスキルスロットに切り替えます").viewItemStack();

    ItemStack AttributeMenuIcon = ViewableItemStack.create(AttributeMenu.display,Material.CRAFTING_TABLE, "設定メニューを開きます").viewItemStack();
    ItemStack SettingMenuIcon = ViewableItemStack.create(SettingMenu.display,Material.CRAFTING_TABLE, "設定メニューを開きます").viewItemStack();

    String display = "ユーザーメニュー";

    @Override
    default String getGUIDisplay() {
        return display;
    }

    @Override
    default int getSize() {
        return 3;
    }

    @Override
    default ItemStack[] getContent() {
        ItemStack[] content = new ItemStack[getSize()*9];
        content[0] = ItemInventoryIcon;
        content[1] = RuneInventoryIcon;
        content[2] = PetInventoryIcon;
        content[3] = SkillSlotIcon;

        content[18] = AttributeMenuIcon;
        content[26] = SettingMenuIcon;

        for (int i = 9; i < 18; i++) {
            content[i] = GenericConfig.GUIPartition;
        }
        return content;
    }

    @Override
    default void onClick(Inventory clickedInv, ItemStack clickedItem, ClickType clickType, InventoryAction action, int slot) {
        //上段
        if (clickedItem.equals(ItemInventoryIcon)) {
            getPlayerData().setViewInventory(SomInventoryType.ItemInventory);
        } else if (clickedItem.equals(RuneInventoryIcon)) {
            getPlayerData().setViewInventory(SomInventoryType.RuneInventory);
        } else if (clickedItem.equals(PetInventoryIcon)) {
            getPlayerData().setViewInventory(SomInventoryType.PetInventory);
        } else if (clickedItem.equals(SkillSlotIcon)) {
            getPlayerData().setViewInventory(SomInventoryType.SkillSlot);
        }

        //下段
        if (clickedItem.equals(AttributeMenuIcon)) {
            getPlayerData().getAttributeMenu().openGUI();
        } else if (clickedItem.equals(SettingMenuIcon)) {
            getPlayerData().getSettingMenu().openGUI();
        }
    }
}
