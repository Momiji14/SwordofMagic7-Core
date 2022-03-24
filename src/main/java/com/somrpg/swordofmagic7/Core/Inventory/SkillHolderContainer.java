package com.somrpg.swordofmagic7.Core.Inventory;

import com.somrpg.swordofmagic7.Core.DataBase.DataBase;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.ViewableItemStack;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
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
}
