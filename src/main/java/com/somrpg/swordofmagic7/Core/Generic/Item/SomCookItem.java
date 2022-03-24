package com.somrpg.swordofmagic7.Core.Generic.Item;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.GenericStatus;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.checkerframework.checker.nullness.qual.NonNull;

public class SomCookItem extends SomPotionItem implements Cloneable {

    private GenericStatus fixed;
    private GenericStatus multiply;

    public SomCookItem(@NonNull SomItemStack data, @NonNull ItemCategory itemCategory, int sell) {
        super(data, itemCategory, sell);
    }

    @Override
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

    public SomCookItem cloneCookItem() {
        return clone();
    }

    @Override
    public SomCookItem clone() {
        SomCookItem clone = (SomCookItem) super.clone();
        // TODO: このクローンが元の内部を変更できないようにミュータブルな状態をここにコピーします
        return clone;
    }
}
