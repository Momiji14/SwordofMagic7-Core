package com.somrpg.swordofmagic7.Core.Generic;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.ViewableItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public interface GenericConfig {
    String DataBasePath = "M:\\Minecraft\\Server\\SwordofMagic7\\DataBase\\";
    int MaxItemStackAmount = 100;
    int ItemInventoryMaxSlot = 256;
    int RuneInventoryMaxSlot = 256;
    int PetInventoryMaxSlot = 64;

    int PlayerMaxLevel = 50;
    int ClassMaxLevel = 15;
    int LifeMaxLevel = 30;

    ItemStack AirItem = new ItemStack(Material.AIR);
    ItemStack UpScrollIcon = ViewableItemStack.create("上へスクロール", Material.ITEM_FRAME).viewItemStack();
    ItemStack DownScrollIcon = ViewableItemStack.create("下へスクロール", Material.ITEM_FRAME).viewItemStack();
    ItemStack UserMenuIcon = ViewableItemStack.create("ユーザーメニュ", Material.ITEM_FRAME, "ユーザーメニュを開きます").viewItemStack();

    ItemStack GUIPartition = ViewableItemStack.create(" ",Material.IRON_BARS, 1).viewItemStack();
    ItemStack AnvilUIFlame = ViewableItemStack.create(" ", Material.IRON_BARS, 3).viewItemStack();
    ItemStack NoneFlame = ViewableItemStack.create(" ", Material.IRON_BARS, 4).viewItemStack();
}
