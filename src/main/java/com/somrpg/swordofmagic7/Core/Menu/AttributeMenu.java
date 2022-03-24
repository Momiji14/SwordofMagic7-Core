package com.somrpg.swordofmagic7.Core.Menu;

import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.ViewableItemStack;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static com.somrpg.swordofmagic7.Core.Generic.DecoContent.decoLore;
import static com.somrpg.swordofmagic7.Core.Generic.DecoContent.decoPartition;
import static com.somrpg.swordofmagic7.Core.Generic.DecoFormat.ScaleDigit;

public interface AttributeMenu extends BaseMenu {

    static String decoAttributeLore(String prefix, double value) {
        return decoLore(prefix) + "+" + ScaleDigit(value*100, 2) + "%";
    }

    String display = "アトリビュート";

    @Override
    default String getGUIDisplay() {
        return display;
    }

    @Override
    default int getSize() {
        return 1;
    }

    @Override
    default ItemStack[] getContent() {
        ItemStack[] content = new ItemStack[getSize()*9];
        content[0] = getSTR().viewItemStack();
        content[1] = getINT().viewItemStack();
        content[2] = getDEX().viewItemStack();
        content[3] = getTEC().viewItemStack();
        content[4] = getSPI().viewItemStack();
        content[5] = getVIT().viewItemStack();
        return content;
    }

    @Override
    default void onClick(Inventory clickedInv, ItemStack clickedItem, ClickType clickType, InventoryAction action, int slot) {
        int point = getPlayerData().getAttributePoint();
        if (point > 0) {
            int x = Math.min(point, clickType.isShiftClick() ? 10 : 1);
            getPlayerData().addAttributePoint(-x);
            switch (slot) {
                case 0 -> getPlayerData().addSTR(x);
                case 1 -> getPlayerData().addINT(x);
                case 2 -> getPlayerData().addDEX(x);
                case 3 -> getPlayerData().addTEC(x);
                case 4 -> getPlayerData().addSPI(x);
                case 5 -> getPlayerData().addVIT(x);
            }
        }
    }

    String partition = decoPartition("ステータス情報");

    default ViewableItemStack getSTR() {
        List<String> lore = new ArrayList<>();
        lore.add("物理攻撃に関するステータスに影響します");
        lore.add(partition);
        lore.add(decoAttributeLore("物理ダメージ倍率", GenericConfig.AttributeValue_STR_DamageATK));
        lore.add(decoAttributeLore("攻撃力", GenericConfig.AttributeValue_STR_ATK));
        return ViewableItemStack.create("筋力", Material.RED_DYE, lore);
    }

    default ViewableItemStack getINT() {
        List<String> lore = new ArrayList<>();
        lore.add("魔法攻撃に関するステータスに影響します");
        lore.add(partition);
        lore.add(decoAttributeLore("魔法ダメージ倍率", GenericConfig.AttributeValue_INT_DamageMAT));
        lore.add(decoAttributeLore("魔法ダメージ軽減", GenericConfig.AttributeValue_INT_ResistMAT));
        lore.add(decoAttributeLore("攻撃力", GenericConfig.AttributeValue_INT_ATK));
        return ViewableItemStack.create("魔力", Material.PURPLE_DYE, lore);
    }

    default ViewableItemStack getDEX() {
        List<String> lore = new ArrayList<>();
        lore.add("回避とクリティカルダメージに影響します");
        lore.add(partition);
        lore.add(decoAttributeLore("回避", GenericConfig.AttributeValue_DEX_EVA));
        lore.add(decoAttributeLore("クリティカルダメージ", GenericConfig.AttributeValue_DEX_CriticalDamage));
        return ViewableItemStack.create("敏捷", Material.YELLOW_DYE, lore);
    }

    default ViewableItemStack getTEC() {
        List<String> lore = new ArrayList<>();
        lore.add("クリティカル発生と命中に影響します");
        lore.add(partition);
        lore.add(decoAttributeLore("命中", GenericConfig.AttributeValue_TEC_ACC));
        lore.add(decoAttributeLore("クリティカル発生", GenericConfig.AttributeValue_TEC_CriticalRate));
        return ViewableItemStack.create("技量", Material.LIME_DYE, lore);
    }

    default ViewableItemStack getSPI() {
        List<String> lore = new ArrayList<>();
        lore.add("マナと魔法防御・治癒力に影響します");
        lore.add(partition);
        lore.add(decoAttributeLore("最大マナ", GenericConfig.AttributeValue_SPI_MaxMana));
        lore.add(decoAttributeLore("マナ自動回復", GenericConfig.AttributeValue_SPI_ManaRegen));
        lore.add(decoAttributeLore("治癒力", GenericConfig.AttributeValue_SPI_HLP));
        lore.add(decoAttributeLore("魔法ダメージ軽減", GenericConfig.AttributeValue_SPI_ResistMAT));
        return ViewableItemStack.create("精神", Material.LIGHT_BLUE_DYE, lore);
    }

    default ViewableItemStack getVIT() {
        List<String> lore = new ArrayList<>();
        lore.add("体力と防御・クリティカル耐性に影響します");
        lore.add(partition);
        lore.add(decoAttributeLore("最大体力", GenericConfig.AttributeValue_VIT_MaxHealth));
        lore.add(decoAttributeLore("体力自動回復", GenericConfig.AttributeValue_VIT_HealthRegen));
        lore.add(decoAttributeLore("防御力", GenericConfig.AttributeValue_VIT_DEF));
        lore.add(decoAttributeLore("物理ダメージ軽減", GenericConfig.AttributeValue_VIT_ResistATK));
        lore.add(decoAttributeLore("クリティカル耐性", GenericConfig.AttributeValue_VIT_CriticalResist));
        return ViewableItemStack.create("活力", Material.ORANGE_DYE, lore);
    }
}
