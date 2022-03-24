package com.somrpg.swordofmagic7.Core.Player.Inventory;

import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Generic.Item.BaseItem;
import com.somrpg.swordofmagic7.Core.Generic.Item.EquipmentItem;
import com.somrpg.swordofmagic7.Core.Generic.Item.SomEquipmentSlot;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ItemInventoryContainer extends BaseInventory implements ItemInventory {

    private final HashMap<SomEquipmentSlot, EquipmentItem> equipmentSlot = new HashMap<>();

    public ItemInventoryContainer(@NonNull PlayerData playerData) {
        super(playerData, SomInventoryType.ItemInventory);
    }

    @Override
    public void addContent(@NonNull BaseItem itemData) {
        if (getList().size() < GenericConfig.ItemInventoryMaxSlot) {
            for (SomItemStack itemStack : getList()) {
                if (SomItemStack.equal(itemStack, itemData)) {
                    itemStack.addAmount(itemData.getAmount());
                    return;
                }
            }
            getList().add(itemData);
            getPlayerData().viewUpdate();
        } else {
            getPlayerData().sendMessage("§e" + getInventoryType().getDisplay() + "§aが一杯です", SomSound.Nope);
        }
    }


    @Override
    public Map<SomEquipmentSlot, EquipmentItem> getEquipmentSlot() {
        return equipmentSlot;
    }

    @Override
    public void setEquipment(@NonNull EquipmentItem equipmentItem) {
        setEquipment(equipmentItem, true);
    }

    public void setEquipment(@NonNull EquipmentItem equipmentItem, boolean log) {
        SomEquipmentSlot slot = equipmentItem.getEquipmentSlot();
        if (hasEquipment(slot)) addContent(getEquipment(slot));
        EquipmentItem item = equipmentItem.cloneEquipment();
        equipmentSlot.put(slot, item);
        item.setAmount(1);
        removeContent(equipmentItem, 1);
        if (log) getPlayerData().sendMessage("§e[" + equipmentItem.getDisplay() + "]§aを§b装備§aしました", SomSound.Equip);
        getPlayerData().viewUpdate();
    }

    @Override
    public void removeEquipment(@NonNull SomEquipmentSlot slot) {
        if (hasEquipment(slot)) addContent(equipmentSlot.get(slot));
        equipmentSlot.remove(slot);
        getPlayerData().viewUpdate();
    }
}
