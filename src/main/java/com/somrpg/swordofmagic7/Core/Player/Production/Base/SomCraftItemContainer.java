package com.somrpg.swordofmagic7.Core.Player.Production.Base;

import com.somrpg.swordofmagic7.Core.Generic.DecoContent;
import com.somrpg.swordofmagic7.Core.Generic.DecoFormat;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SomCraftItemContainer implements SomCraftItem {

    private final SomItemStack somItemStack;
    private final SomRecipe recipe;

    protected SomCraftItemContainer(SomRecipe recipe, SomItemStack somItemStack) {
        this.recipe = recipe != null ? recipe : SomRecipe.create(new ArrayList<>());
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

    @Override
    public SomRecipe getRecipe() {
        return recipe;
    }

    @Override
    public List<String> toStringList() {
        return recipe.toStringList();
    }
}
