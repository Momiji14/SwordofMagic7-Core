package com.somrpg.swordofmagic7.Core.Player.Interface;

import com.somrpg.swordofmagic7.Core.Equipment.EquipmentItem;
import com.somrpg.swordofmagic7.Core.Player.Container.PlayerEntityContainer;

public interface PlayerStatusCalculation {
    static double levelMultiply(int level) {
        return Math.pow(level, 1.01) + (level-1) * 0.05;
    }

    PlayerData getPlayerData();

    default void statusUpdate() {
        PlayerEntityContainer playerEntity = getPlayerData().getPlayerEntityContainer();
        double multiply = levelMultiply(playerEntity.getLevel());
        double MaxHealth = 120 + multiply*12;
        double HealthRegen = 1;
        double MaxMana = 80 + multiply*8;
        double ManaRegen = 1;
        double ATK = 80 + multiply*8;
        double DEF = 50 + multiply*5;
        double ACC = 10 + multiply;
        double EVA = 2 + multiply*0.2;
        double CriticalRate = 10 + multiply;
        double CriticalResist = 10 + multiply;
        playerEntity.setStatusParameter(MaxHealth, HealthRegen, MaxMana, ManaRegen, ATK, DEF, ACC, EVA, CriticalRate, CriticalResist);
        for (EquipmentItem equipmentItem : getPlayerData().getItemInventory().getEquipmentSlot().values()) {
            playerEntity.addStatusParameter(equipmentItem.getStatusParameter());
        }
    }
}
