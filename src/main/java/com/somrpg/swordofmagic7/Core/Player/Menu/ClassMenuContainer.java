package com.somrpg.swordofmagic7.Core.Player.Menu;

import com.somrpg.swordofmagic7.Core.Player.ClassesSkills.ClassData;
import com.somrpg.swordofmagic7.Core.Player.Skills.SkillData;
import com.somrpg.swordofmagic7.Core.DataBase.ClassDataLoader;
import com.somrpg.swordofmagic7.Core.Generic.DecoContent;
import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.ViewableItemStack;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.somrpg.swordofmagic7.Core.Generic.DecoContent.decoLore;
import static com.somrpg.swordofmagic7.Core.Generic.DecoFormat.ScaleDigit;

public class ClassMenuContainer implements ClassMenu {

    private int selectSlot = -1;
    private final PlayerData playerData;

    public ClassMenuContainer(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public void resetSelectSlot() {
        selectSlot = -1;
    }

    @Override
    public String getGUIDisplay() {
        return "クラスメニュー";
    }

    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }

    @Override
    public int getSize() {
        if (selectSlot == -1) {
            return 1;
        } else {
            return 6;
        }
    }

    @Override
    public ItemStack[] getContent() {
        ItemStack[] itemStacks = new ItemStack[getSize()*9];
        if (selectSlot == -1) {
            for (int i = 0; i < 4; i++) {
                itemStacks[i] = ViewableItemStack.create("スロット["+ (i+1) + "]", Material.END_CRYSTAL, decoLore("必要レベル") + GenericConfig.SlotReqLevel[i]).viewItemStack();
            }
        } else {
            for (Map.Entry<Integer, ClassData> entry : ClassDataLoader.ClassGUI.entrySet()) {
                itemStacks[entry.getKey()] = viewItemStack(entry.getValue());
            }
        }
        return itemStacks;
    }

    public ItemStack viewItemStack(@Nullable ClassData classData) {
        if (classData != null) {
            List<String> lore = new ArrayList<>(classData.getLore());
            lore.add(DecoContent.decoPartition("クラス情報"));
            lore.add(decoLore("短縮表記") + classData.getColor() + classData.getNick());
            lore.add(decoLore("クラスレベル") + getPlayerData().getClassLevel(classData));
            lore.add(decoLore("クラス経験値") + getPlayerData().getClassExpPercent(classData));
            lore.add(DecoContent.decoPartition("スキル"));
            for (SkillData skillData : classData.getSkillList()) {
                lore.add("§7・§e§l" + skillData.getDisplay());
            }
            return ViewableItemStack.create(classData.getDisplay(), classData.getMaterial(), lore).viewItemStack();
        } else {
            return ViewableItemStack.create("ClassesError", Material.BARRIER).viewItemStack();
        }
    }

    @Override
    public void onClick(Inventory clickedInv, ItemStack clickedItem, ClickType clickType, InventoryAction action, int slot) {
        if (selectSlot == -1) {
            if (playerData.getLevel() >= GenericConfig.SlotReqLevel[slot]) {
                selectSlot = slot;
                openGUI();
            } else {
                playerData.sendMessage("§eLv" + GenericConfig.SlotReqLevel[slot] + "§a以上になると§b転職§aできます", SomSound.Nope);
            }
        } else {
            ClassData classData = ClassDataLoader.ClassGUI.get(slot);
            List<String> reqMessage = new ArrayList<>();
            for (Map.Entry<ClassData, Integer> entry : classData.getReqClass().entrySet()) {
                ClassData reqClass = entry.getKey();
                if (getPlayerData().getClassLevel(entry.getKey()) < entry.getValue()) {
                    reqMessage.add("§7・" + reqClass.getColor() + reqClass.getDisplay() + " §eLv" + entry.getValue() + " §c✖");
                }
            }
            if (reqMessage.size() > 0) {
                playerData.sendMessage(reqMessage, SomSound.Nope);
            } else {
                getPlayerData().setClassSlot(selectSlot, classData);
                playerData.sendMessage("§eスロット[" + (selectSlot+1) + "]§aを" + classData.getColor() + "[" + classData.getDisplay() + "]§aに§b転職§aしました", SomSound.LevelUp) ;
            }
        }
    }

}
