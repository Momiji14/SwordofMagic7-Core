package com.somrpg.swordofmagic7.Core.Player.Menu;

import com.somrpg.swordofmagic7.Core.Generic.Item.BaseItem;
import com.somrpg.swordofmagic7.Core.Generic.Item.EquipmentItem;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.Inventory.SkillHolder;
import com.somrpg.swordofmagic7.Core.Player.Inventory.SkillHolderContainer;
import com.somrpg.swordofmagic7.Core.Player.Inventory.SkillHolderType;
import com.somrpg.swordofmagic7.Core.Player.Inventory.SomInventoryType;
import com.somrpg.swordofmagic7.Core.Player.Skills.SkillData;
import com.somrpg.swordofmagic7.Core.Player.Skills.SkillType;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class SkillSlotMenuContainer implements SkillSlotMenu {

    private final PlayerData playerData;

    public SkillSlotMenuContainer(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public String getGUIDisplay() {
        return display;
    }

    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }

    @Override
    public int getSize() {
        return 6;
    }

    @Override
    public void openProcess() {
        getPlayerData().setViewInventory(SomInventoryType.SkillSlot);
        getPlayerData().viewUpdate();
    }

    Map<Integer, SkillHolder> skillHolderMap = new HashMap<>();
    @Override
    public ItemStack[] getContent() {
        ItemStack[] content = new ItemStack[getSize()*9];
        int i = 0;
        for (SkillData skillData : getPlayerData().getPlayerClass().getSkillsAt(SkillType.Active)) {
            skillHolderMap.put(i, SkillHolder.create(skillData.getId(), SkillHolderType.Skill));
            i++;
        }
        i = (int) (Math.ceil(i/9d)*9);
        for (SomItemStack item : getPlayerData().getItemInventory().getList()) {
            if (item instanceof EquipmentItem) {
                skillHolderMap.put(i, SkillHolder.create(item.getId(), SkillHolderType.Item));
            }
        }
        for (Map.Entry<Integer, SkillHolder> entry : skillHolderMap.entrySet()) {
            content[entry.getKey()] = entry.getValue().viewItemStack(getPlayerData(), -1);
        }
        return content;
    }

    @Override
    public void onClick(Inventory clickedInv, ItemStack clickedItem, ClickType clickType, InventoryAction action, int slot) {

    }
}
