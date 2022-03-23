package com.somrpg.swordofmagic7.Core.Menu;

import com.somrpg.swordofmagic7.Core.Generic.DecoContent;
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
        List<String> loreSTR = new ArrayList<>();
        loreSTR.add(decoAttributeLore("物理ダメージ倍率", GenericConfig.AttributeValue_STR_DamageATK));
        loreSTR.add(decoAttributeLore("攻撃力", GenericConfig.AttributeValue_STR_ATK));
        content[0] = ViewableItemStack.create("筋力", Material.RED_DYE, loreSTR).viewItemStack();
        
        List<String> loreINT = new ArrayList<>();
        loreINT.add(decoAttributeLore("魔法ダメージ倍率", GenericConfig.AttributeValue_INT_DamageMAT));
        loreINT.add(decoAttributeLore("魔法ダメージ軽減", GenericConfig.AttributeValue_INT_ResistMAT));
        loreINT.add(decoAttributeLore("攻撃力", GenericConfig.AttributeValue_INT_ATK));
        content[1] = ViewableItemStack.create("魔力", Material.PURPLE_DYE, loreINT).viewItemStack();
        
        List<String> loreDEX = new ArrayList<>();
        loreDEX.add(decoAttributeLore("回避", GenericConfig.AttributeValue_DEX_EVA));
        loreDEX.add(decoAttributeLore("クリティカルダメージ", GenericConfig.AttributeValue_DEX_CriticalDamage));
        content[2] = ViewableItemStack.create("敏捷", Material.YELLOW_DYE, loreDEX).viewItemStack();

        List<String> loreTEC = new ArrayList<>();
        loreTEC.add(decoAttributeLore("命中", GenericConfig.AttributeValue_TEC_ACC));
        loreTEC.add(decoAttributeLore("クリティカル発生", GenericConfig.AttributeValue_TEC_CriticalRate));
        content[3] = ViewableItemStack.create("技量", Material.LIME_DYE, loreTEC).viewItemStack();

        List<String> loreSPI = new ArrayList<>();
        loreSPI.add(decoAttributeLore("最大マナ", GenericConfig.AttributeValue_SPI_MaxMana));
        loreSPI.add(decoAttributeLore("マナ自動回復", GenericConfig.AttributeValue_SPI_ManaRegen));
        loreSPI.add(decoAttributeLore("治癒力", GenericConfig.AttributeValue_SPI_HLP));
        loreSPI.add(decoAttributeLore("魔法ダメージ軽減", GenericConfig.AttributeValue_SPI_ResistMAT));
        content[4] = ViewableItemStack.create("精神", Material.LIGHT_BLUE_DYE, loreSPI).viewItemStack();

        List<String> loreVIT = new ArrayList<>();
        loreVIT.add(decoAttributeLore("最大体力", GenericConfig.AttributeValue_VIT_MaxHealth));
        loreVIT.add(decoAttributeLore("体力自動回復", GenericConfig.AttributeValue_VIT_HealthRegen));
        loreVIT.add(decoAttributeLore("防御力", GenericConfig.AttributeValue_VIT_DEF));
        loreVIT.add(decoAttributeLore("物理ダメージ軽減", GenericConfig.AttributeValue_VIT_ResistATK));
        loreVIT.add(decoAttributeLore("クリティカル耐性", GenericConfig.AttributeValue_VIT_CriticalResist));
        content[5] = ViewableItemStack.create("活力", Material.ORANGE_DYE, loreVIT).viewItemStack();
        
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
}
