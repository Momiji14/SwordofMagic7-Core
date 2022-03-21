package com.somrpg.swordofmagic7.Core.Generic;

import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;

public interface DecoContent {
    static String colorDef(String string, String def) {
        if (!string.contains("§")) {
            string = def + string;
        }
        return string;
    }

    private static String decoText(String text, String color) {
        int flame = 6 - Math.round(text.length() / 2f);
        StringBuilder deco = new StringBuilder("===");
        deco.append("=".repeat(Math.max(0, flame)));
        return "§6§l§m" + deco + "§6§l[[|§r " + colorDef(text, color) + "§r §6§l|]]§m" + deco;
    }

    static String decoDisplay(String display) {
        return decoText(display, "§e§l");
    }

    static String decoPartition(String display) {
        return decoText(display, "§3§l");
    }

    static String decoLore(String lore) {
        return "§7・" + colorDef(lore, "§e§l") + "§7: §a§l";
    }

    static List<String> colorLore(List<String> lore) {
        List<String> coloredLore = new ArrayList<>();
        for (String str : lore) {
            coloredLore.add(colorDef(str, "§a§l"));
        }
        return coloredLore;
    }

    static List<Component> loreToComponent(List<String> lore) {
        List<Component> components = new ArrayList<>();
        for (String str : lore) {
            components.add(Component.text(str));
        }
        return components;
    }

    static String decoBrackets(String string, String color) {
        return color + "[" + string + color + "]";
    }
}
