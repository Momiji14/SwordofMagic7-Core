package com.somrpg.swordofmagic7.Core.Inventory;

import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Item.BaseItem;
import com.somrpg.swordofmagic7.Core.Item.RuneItem;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;

import java.util.ArrayList;
import java.util.List;

public class RuneInventory extends BaseInventory {

    private final List<RuneItem> activeRune = new ArrayList<>();

    public RuneInventory(PlayerData playerData) {
        super(playerData, SomInventoryType.RuneInventory);
    }

    @Override
    public void addContent(SomItemStack itemData) {
        if (itemData instanceof BaseItem item) {
            if (getList().size() < GenericConfig.RuneInventoryMaxSlot) {
                getList().add(item);
            } else {
                getPlayerData().sendMessage("§e" + getInventoryType().getDisplay() + "§aが一杯です", SomSound.Nope);
            }
        }
    }

    public List<RuneItem> getActiveRune() {
        return activeRune;
    }
}
