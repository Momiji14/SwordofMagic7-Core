package com.somrpg.swordofmagic7.Core.Player.Production.Base;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;

import java.util.ArrayList;
import java.util.List;

public class SomRecipeContainer implements SomRecipe {

    private final List<SomItemStack> recipe;

    public SomRecipeContainer(List<SomItemStack> recipes) {
        this.recipe = recipes != null ? recipes : new ArrayList<>();
    }

    @Override
    public List<SomItemStack> getReqStack() {
        return recipe;
    }

    @Override
    public List<String> toStringList(boolean bold) {
        List<String> list = new ArrayList<>();
        String boldText = bold ? "§l" : "";
        for (SomItemStack recipe : getReqStack()) {
            list.add("§7・§e" + boldText + recipe.getDisplay() + "§a"  + boldText + "x" + recipe.getAmount());
        }
        return list;
    }
}

