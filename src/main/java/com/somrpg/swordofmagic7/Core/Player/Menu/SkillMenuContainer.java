package com.somrpg.swordofmagic7.Core.Player.Menu;

import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Player.ClassesSkills.ClassData;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.Skills.SkillData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class SkillMenuContainer implements SkillMenu {

    private final PlayerData playerData;

    SkillMenuContainer(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public String getGUIDisplay() {
        return "スキルメニュー";
    }

    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }

    @Override
    public int getSize() {
        return GenericConfig.ClassSlot;
    }

    Map<Integer, SkillData> contentMap = new HashMap<>();
    @Override
    public ItemStack[] getContent() {
        contentMap.clear();
        int row = 0;
        for (ClassData classData : getPlayerData().getPlayerClass().getClassSlot()) {
            int i = 0;
            int i2 = 8;
            for (SkillData skillData : classData.getSkillList()) {
                if (skillData.getSkillType().isActive()) {
                    contentMap.put(row*9+i, skillData);
                    i++;
                } else if (skillData.getSkillType().isPassive()) {
                    contentMap.put(row*9+i2, skillData);
                    i--;
                }
            }
            row++;
        }
        ItemStack[] content = new ItemStack[getSize()*9];
        for (Map.Entry<Integer, SkillData> entry : contentMap.entrySet()) {
            content[entry.getKey()] = entry.getValue().viewItemStack();
        }
        return content;
    }

    @Override
    public void onClick(Inventory clickedInv, ItemStack clickedItem, ClickType clickType, InventoryAction action, int slot) {
        if (contentMap.containsKey(slot)) {
            SkillData skillData = contentMap.get(slot);
            if (skillData.getSkillType().isActive()) {

            } else if (skillData.getSkillType().isPassive()) {
                getPlayerData().sendMessage("§e[" + skillData.getDisplay() + "]§aは§bパッシブスキル§aです", SomSound.Nope);
            }
        }
    }
}
