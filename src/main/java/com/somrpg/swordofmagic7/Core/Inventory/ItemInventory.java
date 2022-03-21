package com.somrpg.swordofmagic7.Core.Inventory;

import com.somrpg.swordofmagic7.Core.Equipment.EquipmentItem;
import com.somrpg.swordofmagic7.Core.Equipment.SomEquipmentSlot;
import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Item.BaseItem;
import com.somrpg.swordofmagic7.Core.Item.ItemCategory;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;

import java.util.HashMap;
import java.util.Map;

import static com.somrpg.swordofmagic7.Core.Generic.DecoContent.decoBrackets;

public class ItemInventory extends BaseInventory implements SomEquipmentInventory {

    private final HashMap<SomEquipmentSlot, EquipmentItem> equipmentSlot = new HashMap<>();

    public ItemInventory(PlayerData playerData, SomInventoryType inventoryType) {
        super(playerData, inventoryType);
    }

    @Override
    public void addContent(SomItemStack itemData) {
        if (itemData instanceof BaseItem item) {
            if (getList().size() < GenericConfig.ItemInventoryMaxSlot) {
                if (hasContent(item.getId())) {
                    ItemCategory category = item.getItemCategory();
                    if (category.isEquipment() || category.isTool()) {
                        addContent(item);
                    } else {
                        getContent(item.getId()).addAmount(item.getAmount());
                    }
                } else {
                    addContent(item);
                }
            } else {
                getPlayerData().sendMessage(decoBrackets(getInventoryType().getDisplay(), "§e"), SomSound.Nope);
            }
        }
    }

    @Override
    public Map<SomEquipmentSlot, EquipmentItem> getEquipmentSlot() {
        return equipmentSlot;
    }
}
