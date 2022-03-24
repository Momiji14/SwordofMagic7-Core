package com.somrpg.swordofmagic7.Core.Player.Inventory;

import com.somrpg.swordofmagic7.Core.DataBase.SkillDataLoader;
import com.somrpg.swordofmagic7.Core.DataBase.SomItemDataLoader;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.ViewableItemStack;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.Skills.SkillData;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SkillHolderContainer implements SkillHolder {
    private String key;
    private SkillHolderType type = SkillHolderType.None;

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public SkillHolderType getType() {
        return type;
    }

    @Override
    public void setType(SkillHolderType type) {
        this.type = type;
    }

    @Override
    public ItemStack viewItemStack(PlayerData playerData, int slot) {
        ItemStack item = ViewableItemStack.create("§7§lスロット[" + (slot+1) + "]", Material.IRON_BARS, 1).viewItemStackNonDeco();

        switch (getType()) {
            case Skill -> {
                SkillData skillData = SkillDataLoader.getSkillData(getKey());
                item = skillData.viewItemStack();
            }
            case Item -> {
                SomItemStack itemStack = playerData.getItemInventory().getContent(getKey());
                if (itemStack == null) itemStack = SomItemDataLoader.getItem(getKey());
                if (itemStack != null) item = itemStack.viewItemStack();
            }
        }
        return item;
    }
}
