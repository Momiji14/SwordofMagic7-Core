package com.somrpg.swordofmagic7.Core.Production.Base;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.ViewableItemStack;
import com.somrpg.swordofmagic7.Core.Menu.BaseMenu;
import com.somrpg.swordofmagic7.Core.Production.CheckReturn;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public interface SomCraftDisplay extends BaseMenu {

    ItemStack NextPage = ViewableItemStack.create("次ページ", Material.BOOK).viewItemStack();
    ItemStack PreviousPage = ViewableItemStack.create("前ページ", Material.BOOK).viewItemStack();

    static ItemStack CraftGUIAmountChangeIcon(int amount) {
        String display = String.valueOf(amount);
        if (amount > 0 ) display = "+" + display;
        return ViewableItemStack.create(display, Material.IRON_BARS, 2).viewItemStack();
    }

    static ItemStack CraftGUIAmountIcon(int amount) {
        return ViewableItemStack.create("§ex" + amount, Material.GOLD_NUGGET).viewItemStack();
    }

    void open(CraftData craftData);

    int getRawSize();

    int getOffset();

    CheckReturn isCraftAble(SomCraftItem craftItem, CheckReturn checkReturn);

    void craft(SomCraftItem craftItem, CheckReturn checkReturn);

    Map<Integer, CraftSlot> getCraftSlots();

    void onClickProduct(SomCraftItem craftItem);

    String getChar();

    void setAmount(int amount);

    int getAmount();

    void setPage(int page);

    int getPage();

    void setMaxPage(int maxPage);

    int getMaxPage();
}
