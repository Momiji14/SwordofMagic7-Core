package com.somrpg.swordofmagic7.Core.Generic;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.ViewableItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public interface GenericConfig {
    String DataBasePath = "M:\\Minecraft\\Server\\SwordofMagic7\\DataBase\\";
    int MaxItemStackAmount = 100;
    int ItemInventoryMaxSlot = 256;
    int RuneInventoryMaxSlot = 256;
    int PetInventoryMaxSlot = 64;

    int GenericDigit = 2;

    int ClassSlot = 4;
    int[] SlotReqLevel = {1, 10, 30, 50};
    int PlayerMaxLevel = 50;
    int ClassMaxLevel = 15;
    int LifeMaxLevel = 30;

    Vector VectorUp = new Vector(0, 1, 0);
    Vector VectorDown = new Vector(0, -1, 0);

    ItemStack AirItem = new ItemStack(Material.AIR);

    ItemStack GUIPartition = ViewableItemStack.create(" ", Material.IRON_BARS, 1).viewItemStackNonDeco();
    ItemStack AnvilUIFlame = ViewableItemStack.create(" ", Material.IRON_BARS, 3).viewItemStackNonDeco();
    ItemStack NoneFlame = ViewableItemStack.create(" ", Material.IRON_BARS, 4).viewItemStackNonDeco();

    double AttributeValue_STR_ATK = 0.01;
    double AttributeValue_STR_DamageATK = 0.002;
    double AttributeValue_INT_ATK = 0.01;
    double AttributeValue_INT_DamageMAT = 0.0015;
    double AttributeValue_INT_ResistMAT = 0.0005;
    double AttributeValue_DEX_EVA = 0.01;
    double AttributeValue_DEX_CriticalDamage = 0.01;
    double AttributeValue_TEC_ACC = 0.01;
    double AttributeValue_TEC_CriticalRate = 0.01;
    double AttributeValue_SPI_MaxMana = 0.008;
    double AttributeValue_SPI_ManaRegen = 0.0008;
    double AttributeValue_SPI_HLP = 0.01;
    double AttributeValue_SPI_ResistMAT = 0.0015;
    double AttributeValue_VIT_MaxHealth = 0.012;
    double AttributeValue_VIT_HealthRegen = 0.0012;
    double AttributeValue_VIT_DEF = 0.01;
    double AttributeValue_VIT_ResistATK = 0.002;
    double AttributeValue_VIT_CriticalResist = 0.002;
}

