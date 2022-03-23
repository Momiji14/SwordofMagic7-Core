package com.somrpg.swordofmagic7.Core.Production.Base;

import com.somrpg.swordofmagic7.Core.Generic.DecoContent;
import com.somrpg.swordofmagic7.Core.Generic.DecoFormat;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SomCraftItemContainer extends SomRecipeContainer implements SomCraftItem {

    private final SomItemStack somItemStack;

    protected SomCraftItemContainer(SomRecipe recipe, SomItemStack somItemStack) {
        super(recipe.getRecipe());
        this.somItemStack = somItemStack;
    }

    @Override
    public SomItemStack getSomItemStack() {
        return somItemStack;
    }

    @Override
    public ItemStack viewItemStack() {
        ItemStack itemStack = getSomItemStack().viewItemStack();
        List<String> addLore = new ArrayList<>();
        addLore.add(DecoContent.decoPartition(getPartition()));
        addLore.addAll(toStringList());
        DecoFormat.addLore(itemStack, addLore);
        return itemStack;
    }

    @Override
    public String getPartition() {
        return "作成情報";
    }
}
