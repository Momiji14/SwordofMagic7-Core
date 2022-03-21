package com.somrpg.swordofmagic7.Core.Inventory;

import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Pet.PetItem;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;

import static com.somrpg.swordofmagic7.Core.Generic.DecoContent.decoBrackets;

public class PetInventory extends BaseInventory {

    public PetInventory(PlayerData playerData) {
        super(playerData, SomInventoryType.PetInventory);
    }

    @Override
    public void addContent(SomItemStack itemData) {
        if (itemData instanceof PetItem item) {
            if (getList().size() < GenericConfig.PetInventoryMaxSlot) {
                addContent(item);
            } else {
                getPlayerData().sendMessage(decoBrackets(getInventoryType().getDisplay(), "§e"), SomSound.Nope);
            }
        }
    }
}
