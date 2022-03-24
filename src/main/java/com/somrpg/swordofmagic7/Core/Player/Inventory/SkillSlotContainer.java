package com.somrpg.swordofmagic7.Core.Player.Inventory;

import com.somrpg.swordofmagic7.Core.DataBase.SomItemDataLoader;
import com.somrpg.swordofmagic7.Core.Generic.Item.SomPotionItem;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.ViewableItemStack;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
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
        ItemStack item = ViewableItemStack.create("§7§lスロット[" + (slot+1) + "]", Material.IRON_BARS, 1).viewItemStackNonDeco();

        switch (getSkillSlot(slot).getType()) {
            case Skill -> {

            }
            case Item -> {
                SomItemStack itemStack = playerData.getItemInventory().getContent(getSkillSlot(slot).getKey());
                if (itemStack == null) itemStack = SomItemDataLoader.getItem(getSkillSlot(slot).getKey());
                if (itemStack != null) item = itemStack.viewItemStack();
            }
        }
        return item;
    }

    @Override
    public void SkillSlotUse(int slot) {
        switch (getSkillSlot(slot).getType()) {
            case None -> playerData.sendMessage("§eスロット[" + (slot+1) + "]§aは§bセット§aされていません", SomSound.Nope);
            case Item -> {
                SomItemStack item = playerData.getItemInventory().getContent(getSkillSlot(slot).getKey());
                if (item instanceof SomPotionItem potionItem) {
                    potionItem.use(playerData);
                }
            }
            case Skill -> {

            }
        }
    }

    @Override
    public SkillHolder getSkillSlot(int slot) {
        if (skillSlot[slot] == null) skillSlot[slot] = new SkillHolderContainer();
        return skillSlot[slot];
    }

    @Override
    public void viewTop() {
        int slot = 9;
        for (int i = 8; i < 32; i++) {
            playerData.getPlayer().getInventory().setItem(slot, viewItemStack(i));
            slot++;
            if (slot == 17 || slot == 26) slot++;
        }
    }

    @Override
    public void viewBottom() {
        int slot = 0;
        for (int i = 0; i < 8; i++) {
            playerData.getPlayer().getInventory().setItem(slot, viewItemStack(i));
            slot++;
        }
    }

    @Override
    public void ScrollUp() {
        SkillHolder[] skillSlotOld = skillSlot.clone();
        System.arraycopy(skillSlotOld, 8, skillSlot, 0, 24);
        System.arraycopy(skillSlotOld, 0, skillSlot, 24, 8);
        SomSound.Tick.play(playerData.getPlayer());
    }

    @Override
    public void ScrollDown() {
        SkillHolder[] skillSlotOld = skillSlot.clone();
        System.arraycopy(skillSlotOld, 0, skillSlot, 8, 24);
        System.arraycopy(skillSlotOld, 24, skillSlot, 0, 8);
        SomSound.Tick.play(playerData.getPlayer());
    }

    @Override
    public List<String> toDataString() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            data.add(getSkillSlot(i).toDataString());
        }
        return data;
    }

    @Override
    public void fromDataString(List<String> data) {
        int i = 0;
        for (String str : data) {
            skillSlot[i] = SkillHolder.fromDataString(str);
            i++;
        }
    }
}
