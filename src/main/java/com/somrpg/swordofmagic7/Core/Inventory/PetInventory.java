package com.somrpg.swordofmagic7.Core.Inventory;

import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Pet.PetItem;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.checkerframework.checker.nullness.qual.NonNull;

public class PetInventory extends BaseInventory {

    public PetInventory(PlayerData playerData) {
        super(playerData, SomInventoryType.PetInventory);
    }

    @Override
    public void addContent(@NonNull SomItemStack itemData) {
        if (itemData instanceof PetItem item) {
            if (getList().size() < GenericConfig.PetInventoryMaxSlot) {
                getList().add(item);
            } else {
                getPlayerData().sendMessage("§e" + getInventoryType().getDisplay() + "§aが一杯です", SomSound.Nope);
            }
        }
    }
}
