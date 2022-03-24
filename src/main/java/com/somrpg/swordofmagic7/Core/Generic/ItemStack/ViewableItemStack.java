package com.somrpg.swordofmagic7.Core.Generic.ItemStack;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.somrpg.swordofmagic7.Core.Generic.DecoContent;
import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Generic.Item.BaseItem;
import com.somrpg.swordofmagic7.Core.Generic.Item.EquipmentItem;
import com.somrpg.swordofmagic7.Core.Generic.Item.RuneItem;
import com.somrpg.swordofmagic7.Core.SomCore;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.somrpg.swordofmagic7.Core.Generic.DecoContent.decoLore;
import static com.somrpg.swordofmagic7.Core.Generic.DecoContent.decoPartition;
import static com.somrpg.swordofmagic7.Core.Generic.DecoFormat.ScaleDigit;

public class ViewableItemStack {

    private final String display;
    private Material material;
    private String materialData;
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

    public void setMaterialData(String materialData) {
        this.materialData = materialData;
    }

    public String getMaterialData() {
        return materialData;
    }

    public ItemStack viewItemStack() {
        return viewItemStack(amount);
    }

    public ItemStack viewItemStack(int amount) {
        return viewItemStack(amount, 0);
    }

    public ItemStack viewItemStackDigit(int digit) {
        return viewItemStack(amount, digit);
    }

    public ItemStack viewItemStack(int amount, int digit) {
        ItemStack item = new ItemStack(getMaterial());
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(getDecoDisplay()));
        List<String> viewLore = new ArrayList<>(getDecoLore());
        if (this instanceof BaseItem itemData) {
            viewLore.add(decoPartition("アイテム情報"));
            viewLore.add(decoLore("カテゴリ") + itemData.getItemCategory().getDisplay());
            viewLore.add(decoLore("売値") + itemData.getSell());
            viewLore.add(DecoContent.decoPartition("スタック情報"));
            viewLore.add(decoLore("所持個数") + getAmount());
            viewLore.add(decoLore("総価値") + itemData.getSell() * getAmount());
        }
        if (this instanceof EquipmentItem itemData) {
            viewLore.add(decoPartition("装備情報"));
            viewLore.add(decoLore("装備種") + itemData.getEquipmentItemCategory().getDisplay());
            viewLore.add(decoLore("装備部位") + itemData.getEquipmentItemCategory().getEquipmentSlot().getDisplay());
            viewLore.addAll(itemData.getStatusParameter().toStringList(digit));
            viewLore.add(decoLore("強化値") + itemData.getPlus());
            viewLore.add(decoLore("必要レベル") + itemData.getReqLevel());
            viewLore.add(decoPartition("ルーンスロット"));
            for (int i = 0; i < itemData.getRuneSlot(); i++) {
                if (itemData.getRune().size() > i) {
                    RuneItem runeItem = itemData.getRune().get(i);
                    viewLore.add("§7・§e§l" + runeItem.getDisplay() + " " + runeItem.getLevel() + " " + ScaleDigit(runeItem.getQuality(), digit));
                } else {
                    viewLore.add("§7・§lルーン未装着");
                }
            }
        }
        meta.lore(DecoContent.loreToComponent(viewLore));
        meta.setUnbreakable(true);
        meta.setCustomModelData(customModelData);
        for (ItemFlag flag : ItemFlag.values()) {
            meta.addItemFlags(flag);
        }
        if (material == Material.PLAYER_HEAD) {
            SkullMeta skullMeta = (SkullMeta) meta;
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            profile.getProperties().put("textures", new Property("textures", getMaterialData()));
            try {
                Field field = skullMeta.getClass().getDeclaredField("profile");
                field.setAccessible(true);
                field.set(skullMeta, profile);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                SomCore.log("プレイヤへッドのロード時にエラーが発生しました -> " + getDisplay());
            }
            item.setItemMeta(skullMeta);
        } else {
            item.setItemMeta(meta);
        }
        amount = Math.max(1, Math.min(GenericConfig.MaxItemStackAmount, amount));
        item.setAmount(amount);
        return item;
    }

    public ItemStack viewItemStackNonDeco() {
        return viewItemStackNonDeco(amount);
    }

    public ItemStack viewItemStackNonDeco(int amount) {
        ItemStack item = new ItemStack(getMaterial(), amount);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(getDisplay()));
        meta.lore(DecoContent.loreToComponent(getLore()));
        meta.setCustomModelData(customModelData);
        for (ItemFlag flag : ItemFlag.values()) {
            meta.addItemFlags(flag);
        }
        item.setItemMeta(meta);
        return item;
    }
}
