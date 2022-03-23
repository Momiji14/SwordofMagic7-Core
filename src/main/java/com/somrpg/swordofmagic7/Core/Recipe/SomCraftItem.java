package com.somrpg.swordofmagic7.Core.Recipe;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;

import java.util.List;

public class SomCraftItem extends SomRecipe {

    private final SomItemStack somItemStack;

    SomCraftItem(List<SomItemStack> recipes, SomItemStack somItemStack) {
        super(recipes);
        this.somItemStack = somItemStack;
    }

    public SomItemStack getSomItemStack() {
        return somItemStack;
    }

}
