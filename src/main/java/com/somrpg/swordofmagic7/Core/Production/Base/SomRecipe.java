package com.somrpg.swordofmagic7.Core.Production.Base;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;

import java.util.List;

public interface SomRecipe {
    static SomRecipe create(List<SomItemStack> recipe) {
        return new SomRecipeContainer(recipe);
    }

    void setRecipe(List<SomItemStack> recipes);

    List<SomItemStack> getRecipe();

    List<String> toStringList();
}
