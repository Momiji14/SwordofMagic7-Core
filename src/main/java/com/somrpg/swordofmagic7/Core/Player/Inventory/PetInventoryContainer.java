package com.somrpg.swordofmagic7.Core.Player.Inventory;

import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Entity.Pet.PetItem;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.checkerframework.checker.nullness.qual.NonNull;

public class PetInventoryContainer extends BaseInventory implements PetInventory {

    public PetInventoryContainer(PlayerData playerData) {
        super(playerData, SomInventoryType.PetInventory);
    }

    @Override
    public void addContent(@NonNull PetItem itemData) {
        if (getList().size() < GenericConfig.PetInventoryMaxSlot) {
            getList().add(itemData);
            getPlayerData().viewUpdate();
        } else {
            getPlayerData().sendMessage("§e" + getInventoryType().getDisplay() + "§aが一杯です", SomSound.Nope);
        }
    }

}
