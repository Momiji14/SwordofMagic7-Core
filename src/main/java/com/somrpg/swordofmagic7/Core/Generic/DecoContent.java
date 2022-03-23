package com.somrpg.swordofmagic7.Core.Generic;

import net.kyori.adventure.text.Component;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public interface DecoContent {
    static String colorDef(@NonNull String string, @NonNull String def) {
        if (!string.contains("§")) {
            string = def + string;
        }
        return string;
    }

    private static String decoText(@NonNull String text, @NonNull String color) {
        int flame = 6 - Math.round(text.length() / 2f);
        StringBuilder deco = new StringBuilder("===");
        deco.append("=".repeat(Math.max(0, flame)));
        return "§6§l§m" + deco + "§6§l[[|§r " + colorDef(text, color) + "§r §6§l|]]§m" + deco;
    }

    static String decoDisplay(@NonNull String display) {
        return decoText(display, "§e§l");
    }

    static String decoPartition(@NonNull String display) {
        return decoText(display, "§3§l");
    }

    static String decoLore(@NonNull String lore) {
        return "§7・" + colorDef(lore, "§e§l") + "§7: §a§l";
    }

    static List<String> colorLore(@NonNull List<String> lore) {
        List<String> coloredLore = new ArrayList<>();
        for (String str : lore) {
            coloredLore.add(colorDef(str, "§a§l"));
        }
        return coloredLore;
    }

    static List<Component> loreToComponent(@NonNull List<String> lore) {
        @NonNull List<Component> components = new ArrayList<>();
        for (String str : lore) {
            components.add(Component.text(str));
        }
        return components;
    }

    static String uncolored(String string) {
        return string
                .replace("§0", "")
                .replace("§1", "")
                .replace("§2", "")
                .replace("§3", "")
                .replace("§4", "")
                .replace("§5", "")
                .replace("§6", "")
                .replace("§7", "")
                .replace("§8", "")
                .replace("§9", "")
                .replace("§a", "")
                .replace("§b", "")
                .replace("§c", "")
                .replace("§d", "")
                .replace("§e", "")
                .replace("§f", "")
                .replace("§l", "")
                .replace("§m", "")
                .replace("§n", "")
                .replace("§k", "")
                .replace("§r", "")
                ;

    }
}
