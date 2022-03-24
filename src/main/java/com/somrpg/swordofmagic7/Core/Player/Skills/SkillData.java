package com.somrpg.swordofmagic7.Core.Player.Skills;

import com.somrpg.swordofmagic7.Core.Generic.DecoContent;
import com.somrpg.swordofmagic7.Core.Generic.Item.EquipmentItemCategory;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.ViewableItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static com.somrpg.swordofmagic7.Core.Generic.DecoContent.decoLore;
import static com.somrpg.swordofmagic7.Core.Generic.DecoContent.decoPartition;

public interface SkillData {

    static SkillData create(String id, Material material, String display, List<String> lore, SkillType skillType, int reqLevel, List<SkillParameter> skillParameters, List<EquipmentItemCategory> reqMainHand, List<EquipmentItemCategory> reqOffHand) {
        return new SkillDataContainer(id, material, display, skillType, lore, reqLevel, skillParameters, reqMainHand, reqOffHand);
    }

    String getId();

    Material getMaterial();

    String getDisplay();

    List<String> getLore();

    SkillType getSkillType();

    List<SkillParameter> getSkillParameters();

    double getValue(int i);

    int getValueInt(int i);

    void setMana(int mana);

    int getMana();

    void setStack(int stack);

    int getStack();

    void setCastTime(int castTime);

    int getCastTime();

    void setRigidTime(int rigidTime);

    int getRigidTime();

    void setCoolTime(int coolTime);

    int getCoolTime();

    int getReqLevel();

    List<EquipmentItemCategory> getReqMainHand();

    List<EquipmentItemCategory> getReqOffHand();

    default ItemStack viewItemStack() {
        List<String> lore = new ArrayList<>(getLore());
        lore.add(decoPartition("パラメーター"));
        for (SkillParameter parameter : getSkillParameters()) {
            lore.add(decoLore(parameter.getDisplay()) + parameter.valueView());
        }
        lore.add(decoPartition("スキル情報"));
        lore.add(decoLore("スキルタイプ") + getSkillType().getDisplay());
        if (getSkillType().isActive()) {
            lore.add(decoLore("消費マナ") + getMana());
            lore.add(decoLore("詠唱時間") + getMana());
            lore.add(decoLore("硬直時間") + getMana());
            lore.add(decoLore("再使用時間") + getMana());
        }
        lore.add(decoPartition("使用条件"));
        lore.add(decoLore("クラスレベル") + getReqLevel());
        List<EquipmentItemCategory> list = new ArrayList<>(getReqMainHand());
        list.addAll(getReqOffHand());
        for (EquipmentItemCategory category : list) {
            lore.add("§7・§e§l" + category.getDisplay());
        }
        return ViewableItemStack.create(getDisplay(), getMaterial(), lore).viewItemStack();
    }
}
