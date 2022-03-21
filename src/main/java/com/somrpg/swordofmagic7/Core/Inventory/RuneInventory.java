package com.somrpg.swordofmagic7.Core.Inventory;

import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Item.BaseItem;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;

import static com.somrpg.swordofmagic7.Core.Generic.DecoContent.decoBrackets;

public class RuneInventory extends BaseInventory {

    public RuneInventory(PlayerData playerData, SomInventoryType inventoryType) {
        super(playerData, inventoryType);
    }

    @Override
    public void addContent(SomItemStack itemData) {
        if (itemData instanceof BaseItem item) {
            if (getList().size() < GenericConfig.RuneInventoryMaxSlot) {
                getList().add(item);
            } else {
                getPlayerData().sendMessage(decoBrackets(getInventoryType().getDisplay(), "§e"), SomSound.Nope);
            }
        }
    }
}
