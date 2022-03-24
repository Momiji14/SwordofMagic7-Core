package com.somrpg.swordofmagic7.Core.Inventory;

import com.somrpg.swordofmagic7.Core.DataBase.DataBase;
import com.somrpg.swordofmagic7.Core.Generic.DecoContent;
import com.somrpg.swordofmagic7.Core.Generic.DecoFormat;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.ViewableItemStack;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SkillSlotContainer implements SkillSlot {

    private final PlayerData playerData;
    private final SkillHolder[] skillSlot = new SkillHolder[32];

    public SkillSlotContainer(PlayerData playerData) {
        this.playerData = playerData;
    }

    public ItemStack viewItemStack(int slot) {
        ItemStack item = null;
        if (skillSlot[slot] != null) skillSlot[slot] = new SkillHolder();
        switch (skillSlot[slot].getType()) {
            case None -> item = ViewableItemStack.create("§7§lスロット[" + slot + "]", Material.IRON_BARS, 1).viewItemStackNonDeco();
            case Skill -> {

            }
            case Item -> {
                SomItemStack itemStack = playerData.getItemInventory().getContent(skillSlot[slot].getKey());
                if (itemStack == null) itemStack = DataBase.getSomItemStack(skillSlot[slot].getKey());
                if (itemStack != null) item = itemStack.viewItemStack();
            }
        }
        if (item != null) ViewableItemStack.create("§7§lスロット[" + slot + "]", Material.IRON_BARS, 1).viewItemStackNonDeco();
        return item;
    }

    public void viewTop() {
        int slot = 9;
        for (int i = 8; i < 32; i++) {
            playerData.getPlayer().getInventory().setItem(slot, viewItemStack(slot));
            slot++;
            if (slot == 17 || slot == 26) slot++;
        }
    }

    public void viewBottom() {
        int slot = 0;
        for (int i = 0; i < 8; i++) {
            playerData.getPlayer().getInventory().setItem(slot, viewItemStack(slot));
            slot++;
        }
    }
}
