package com.somrpg.swordofmagic7.Core.Generic;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public interface DecoFormat {
    static String ScaleDigit(double value, int digit) {
        return String.format("%." + digit + "f", value);
    }

    static String ScaleDigit(double value) {
        return ScaleDigit(value, 0);
    }

    static ItemStack addLore(ItemStack itemStack, List<String> addLore) {
        if (itemStack.lore() != null) {
            List<Component> lore = new ArrayList<>(itemStack.lore());
            for (String str : addLore) {
                lore.add(Component.text(str));
            }
            itemStack.lore(lore);
        }
        return itemStack;
    }
}
