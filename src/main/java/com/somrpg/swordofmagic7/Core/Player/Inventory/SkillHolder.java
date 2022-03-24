package com.somrpg.swordofmagic7.Core.Player.Inventory;

import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import org.bukkit.inventory.ItemStack;

public interface SkillHolder {

    static SkillHolder create(String key, SkillHolderType type) {
        SkillHolder skillHolder = new SkillHolderContainer();
        skillHolder.setKey(key);
        skillHolder.setType(type);
        return skillHolder;
    }

    static SkillHolder fromDataString(String data) {
        SkillHolder skillHolder = new SkillHolderContainer();
        if (!data.equals(GenericConfig.NullString)) {
            String[] split = data.split(",");
            skillHolder.setKey(split[0]);
            skillHolder.setType(SkillHolderType.valueOf(split[1]));
        }
        return skillHolder;
    }

    default String toDataString() {
        return getType().isNone() ? GenericConfig.NullString : getKey() + "," + getType().toString();
    }

    void setKey(String key);

    String getKey();

    SkillHolderType getType();

    void setType(SkillHolderType type);

    ItemStack viewItemStack(PlayerData playerData, int slot);
}
