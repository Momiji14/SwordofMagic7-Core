package com.somrpg.swordofmagic7.Core.Player.Production.Base;

import com.somrpg.swordofmagic7.Core.Generic.DecoContent;
import com.somrpg.swordofmagic7.Core.Generic.DecoFormat;
import com.somrpg.swordofmagic7.Core.Generic.Item.BaseItem;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SomCraftItemContainer implements SomCraftItem {

    private final BaseItem item;
    private final SomRecipe recipe;

    protected SomCraftItemContainer(SomRecipe recipe, BaseItem item) {
        this.recipe = recipe != null ? recipe : SomRecipe.create(new ArrayList<>());
        this.item = item;
    }

    @Override
    public BaseItem getItem() {
        return item;
    }

    @Override
    public ItemStack viewItemStack() {
        ItemStack itemStack = getItem().viewItemStack();
        List<String> addLore = new ArrayList<>();
        addLore.add(DecoContent.decoPartition(getPartition()));
        addLore.addAll(toStringList(true));
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
    public List<String> toStringList(boolean bold) {
        return recipe.toStringList(bold);
    }
}
