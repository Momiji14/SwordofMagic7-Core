package com.somrpg.swordofmagic7.Core.Inventory;

import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Item.RuneItem;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class RuneInventoryContainer extends BaseInventory implements RuneInventory {

    private final List<RuneItem> activeRune = new ArrayList<>();

    public RuneInventoryContainer(PlayerData playerData) {
        super(playerData, SomInventoryType.RuneInventory);
    }

    @Override
    public void addContent(@NonNull RuneItem itemData) {
        if (getList().size() < GenericConfig.RuneInventoryMaxSlot) {
            getList().add(itemData);
            getPlayerData().viewUpdate();
        } else {
            getPlayerData().sendMessage("§e" + getInventoryType().getDisplay() + "§aが一杯です", SomSound.Nope);
        }
    }

    public List<RuneItem> getActiveRune() {
        return activeRune;
    }
}
