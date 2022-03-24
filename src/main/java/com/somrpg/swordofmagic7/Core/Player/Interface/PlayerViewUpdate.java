package com.somrpg.swordofmagic7.Core.Player.Interface;

import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.GenericStatus;
import com.somrpg.swordofmagic7.Core.Item.SomEquipmentSlot;

public interface PlayerViewUpdate {

    PlayerData getPlayerData();

    default void viewUpdate() {
        switch (getPlayerData().getPlayerSetting().getViewInventory()) {
            case ItemInventory -> getPlayerData().getItemInventory().viewInventory();
            case RuneInventory -> getPlayerData().getRuneInventory().viewInventory();
            case PetInventory -> getPlayerData().getPetInventory().viewInventory();
            case SkillSlot -> getPlayerData().getSkillSlot().viewTop();
        }
        getPlayerData().getSkillSlot().viewBottom();

        for (SomEquipmentSlot equipmentSlot : SomEquipmentSlot.values()) {
            if (getPlayerData().getItemInventory().hasEquipment(equipmentSlot)) {
                getPlayerData().getPlayer().getInventory().setItem(equipmentSlot.getSlot(), getPlayerData().getItemInventory().getEquipment(equipmentSlot).viewItemStack());
            } else {
                getPlayerData().getPlayer().getInventory().setItem(equipmentSlot.getSlot(), GenericConfig.AirItem);
            }
        }
    }
}
