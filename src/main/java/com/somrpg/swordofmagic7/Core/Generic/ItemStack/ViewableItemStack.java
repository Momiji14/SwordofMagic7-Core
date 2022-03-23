package com.somrpg.swordofmagic7.Core.Generic.ItemStack;

import com.somrpg.swordofmagic7.Core.Generic.DecoContent;
import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ViewableItemStack {

    private final String display;
    private Material material;
    private final List<String> lore;
    private int amount = 1;
    private int customModelData = 0;

    ViewableItemStack(@NonNull String display, @NonNull Material material, @NonNull List<String> lore) {
        this.display = display;
        this.material = material;
        this.lore = lore;
    }

    public static ViewableItemStack create(@NonNull String display, @NonNull Material material, @NonNull List<String> lore) {
        return new ViewableItemStack(display, material, lore);
    }

    public static ViewableItemStack create(@NonNull String display, @NonNull Material material, @NonNull String lore, int customModelData) {
        ViewableItemStack itemStack = new ViewableItemStack(display, material, List.of(lore));
        itemStack.customModelData = customModelData;
        return itemStack;
    }

    public static ViewableItemStack create(@NonNull String display, @NonNull Material material, @NonNull String lore) {
        return new ViewableItemStack(display, material, List.of(lore));
    }

    public static ViewableItemStack create(@NonNull String display, @NonNull Material material) {
        return new ViewableItemStack(display, material, new ArrayList<>());
    }

    public static ViewableItemStack create(@NonNull String display, @NonNull Material material, int customModelData) {
        ViewableItemStack itemStack = new ViewableItemStack(display, material, new ArrayList<>());
        itemStack.customModelData = customModelData;
        return itemStack;
    }

    public String getDisplay() {
        return display;
    }

    public void setMaterial(@NonNull Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    public List<String> getLore() {
        return lore;
    }

    public String getDecoDisplay() {
        return DecoContent.decoDisplay(display);
    }

    public List<String> getDecoLore() {
        return DecoContent.colorLore(lore);
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }

    public int getAmount() {
        return amount;
    }

    public ItemStack viewItemStack() {
        return viewItemStack(amount);
    }

    public ItemStack viewItemStack(int amount) {
        ItemStack item = new ItemStack(getMaterial());
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(getDecoDisplay()));
        meta.lore(DecoContent.loreToComponent(getDecoLore()));
        meta.setUnbreakable(true);
        meta.setCustomModelData(customModelData);
        for (ItemFlag flag : ItemFlag.values()) {
            meta.addItemFlags(flag);
        }
        item.setItemMeta(meta);
        amount = Math.max(1, Math.min(GenericConfig.MaxItemStackAmount, amount));
        item.setAmount(amount);
        return item;
    }

    public ItemStack viewItemStackNonDeco() {
        return viewItemStackNonDeco(amount);
    }

    public ItemStack viewItemStackNonDeco(int amount) {
        ItemStack item = viewItemStack(amount);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(getDisplay()));
        meta.lore(DecoContent.loreToComponent(getLore()));
        item.setItemMeta(meta);
        return item;
    }
}
