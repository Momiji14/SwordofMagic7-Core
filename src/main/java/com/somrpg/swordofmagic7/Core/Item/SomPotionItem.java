package com.somrpg.swordofmagic7.Core.Item;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import org.checkerframework.checker.nullness.qual.NonNull;

public class SomPotionItem extends BaseItem {

    private double Health = 0;
    private double Mana = 0;

    public SomPotionItem(@NonNull SomItemStack data, @NonNull ItemCategory itemCategory, int sell) {
        super(data, itemCategory, sell);
    }

    public void setHealth(double health) {
        Health = health;
    }

    public double getHealth() {
        return Health;
    }

    public void setMana(double mana) {
        Mana = mana;
    }

    public double getMana() {
        return Mana;
    }

    public void use(PlayerData playerData) {

    }
}
