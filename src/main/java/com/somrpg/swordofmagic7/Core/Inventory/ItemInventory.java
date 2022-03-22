package com.somrpg.swordofmagic7.Core.Inventory;

import com.somrpg.swordofmagic7.Core.Equipment.EquipmentItem;
import com.somrpg.swordofmagic7.Core.Equipment.SomEquipmentSlot;
import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Item.BaseItem;
import com.somrpg.swordofmagic7.Core.Item.ItemCategory;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

public class ItemInventory extends BaseInventory implements SomEquipmentInventory {

    private final HashMap<SomEquipmentSlot, EquipmentItem> equipmentSlot = new HashMap<>();

    public ItemInventory(@NonNull PlayerData playerData) {
        super(playerData, SomInventoryType.ItemInventory);
    }

    @Override
    public void addContent(@NonNull SomItemStack itemData) {
        if (itemData instanceof BaseItem item) {
            if (getList().size() < GenericConfig.ItemInventoryMaxSlot) {
                if (hasContent(item.getId())) {
                    ItemCategory category = item.getItemCategory();
                    if (category.isEquipment() || category.isTool()) {
                        getList().add(item);
                    } else {
                        getContent(item.getId()).addAmount(item.getAmount());
                    }
                } else {
                    getList().add(item);
                }
            } else {
                getPlayerData().sendMessage("§e" + getInventoryType().getDisplay() + "§aが一杯です", SomSound.Nope);
            }
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
        equipmentSlot.put(slot, equipmentItem);
        if (log) getPlayerData().sendMessage("§e[" + equipmentItem.getDisplay() + "]§aを§b装備§aしました", SomSound.Equip);
    }
}
