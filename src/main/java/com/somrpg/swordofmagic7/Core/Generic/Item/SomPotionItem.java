package com.somrpg.swordofmagic7.Core.Generic.Item;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
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
        boolean used = false;
        if (playerData.getHealth() < playerData.getMaxHealth()) {
            playerData.addHealth(getHealth());
            used = true;
        }
        if (playerData.getMana() < playerData.getMaxMana()) {
            playerData.addMana(getMana());
            used = true;
        }
        if (used) {
            playerData.getItemInventory().removeContent(this, 1);
            SomSound.Heal.play(playerData.getPlayer());
        } else {
            playerData.sendMessage("§aすでに§e全回復§aしています", SomSound.Nope);
        }
    }
}
