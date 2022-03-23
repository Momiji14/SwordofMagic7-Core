package com.somrpg.swordofmagic7.Core.Menu;

import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.ViewableItemStack;
import com.somrpg.swordofmagic7.Core.Inventory.SomInventoryType;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerUserMenu implements BaseMenu {
    private static final ItemStack ItemInventoryIcon = ViewableItemStack.create(SomInventoryType.ItemInventory.getDisplay(),Material.CHEST, "インベントリ表示をアイテムに切り替えます").viewItemStack();
    private static final ItemStack RuneInventoryIcon = ViewableItemStack.create(SomInventoryType.RuneInventory.getDisplay(),Material.ENDER_CHEST, "インベントリ表示をルーンに切り替えます").viewItemStack();
    private static final ItemStack PetInventoryIcon = ViewableItemStack.create(SomInventoryType.PetInventory.getDisplay(),Material.NOTE_BLOCK, "インベントリ表示をペットケージに切り替えます").viewItemStack();
    private static final ItemStack SkillSlotIcon = ViewableItemStack.create(SomInventoryType.SkillSlot.getDisplay(),Material.END_CRYSTAL, "インベントリ表示をスキルスロットに切り替えます").viewItemStack();
    private static final ItemStack SettingMenuIcon = ViewableItemStack.create(SomInventoryType.SkillSlot.getDisplay(),Material.CRAFTING_TABLE, "設定メニューを開きます").viewItemStack();

    private final PlayerData playerData;

    public PlayerUserMenu(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }

    @Override
    public String getGUIDisplay() {
        return "§lユーザーメニュー";
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public ItemStack[] getContent() {
        ItemStack[] content = new ItemStack[getSize()*9];
        content[0] = ItemInventoryIcon;
        content[1] = RuneInventoryIcon;
        content[2] = PetInventoryIcon;
        content[3] = SkillSlotIcon;
        content[8] = SettingMenuIcon;

        for (int i = 9; i < 18; i++) {
            content[i] = GenericConfig.GUIPartition;
        }
        return content;
    }

    @Override
    public void onClick(Inventory clickedInv, ItemStack clickedItem, int slot) {
        if (clickedItem.equals(ItemInventoryIcon)) {
            getPlayerData().setViewInventory(SomInventoryType.ItemInventory);
        } else if (clickedItem.equals(RuneInventoryIcon)) {
            getPlayerData().setViewInventory(SomInventoryType.RuneInventory);
        } else if (clickedItem.equals(PetInventoryIcon)) {
            getPlayerData().setViewInventory(SomInventoryType.PetInventory);
        } else if (clickedItem.equals(SkillSlotIcon)) {
            getPlayerData().setViewInventory(SomInventoryType.SkillSlot);
        } else if (clickedItem.equals(SettingMenuIcon)) {
            getPlayerData().getSettingMenu().openGUI();
        }
    }
}
