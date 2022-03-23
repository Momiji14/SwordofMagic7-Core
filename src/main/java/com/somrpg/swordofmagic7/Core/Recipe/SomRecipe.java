package com.somrpg.swordofmagic7.Core.Recipe;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;

import java.util.List;

public class SomRecipe {

    private List<SomItemStack> recipe;

    public SomRecipe(List<SomItemStack> recipes) {
        this.recipe = recipes;
    }

    public void setRecipe(List<SomItemStack> recipes) {
        this.recipe = recipes;
    }

    public List<SomItemStack> getRecipe() {
        return recipe;
    }
}

