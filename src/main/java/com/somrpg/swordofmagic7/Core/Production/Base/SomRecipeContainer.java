package com.somrpg.swordofmagic7.Core.Production.Base;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;

import java.util.ArrayList;
import java.util.List;

public class SomRecipeContainer implements SomRecipe {

    private List<SomItemStack> recipe;

    public SomRecipeContainer(List<SomItemStack> recipes) {
        this.recipe = recipes;
    }

    @Override
    public void setRecipe(List<SomItemStack> recipes) {
        this.recipe = recipes;
    }

    @Override
    public List<SomItemStack> getRecipe() {
        return recipe;
    }

    @Override
    public List<String> toStringList() {
        List<String> list = new ArrayList<>();
        for (SomItemStack recipe : getRecipe()) {
            list.add("§7・§e" + recipe.getDisplay() + "§ax" + recipe.getAmount());
        }
        return list;
    }
}

