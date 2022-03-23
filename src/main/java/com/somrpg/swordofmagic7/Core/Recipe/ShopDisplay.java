package com.somrpg.swordofmagic7.Core.Recipe;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;

import java.util.Map;

public class ShopDisplay extends SomCraftDisplay {

    public ShopDisplay(PlayerData playerData, String display, Map<Integer, SomCraftItem> recipes) {
        super(playerData, display, recipes);
    }
}
