package com.somrpg.swordofmagic7.Core.Recipe;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Menu.BaseMenu;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SomCraftDisplay implements BaseMenu {

    private final Map<Integer, SomCraftItem> recipes;
    private final String display;
    private int amount = 1;
    private int page = 1;
    private final PlayerData playerData;

    public SomCraftDisplay(PlayerData playerData, String display, Map<Integer, SomCraftItem> recipes) {
        this.playerData = playerData;
        this.display = display;
        this.recipes = recipes;
    }

    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }

    @Override
    public String getGUIDisplay() {
        return display;
    }

    @Override
    public int getSize() {
        return 6;
    }

    public int getRawSize() {
        return getSize()*9-9;
    }

    public int getOffset() {
        return getRawSize()*(getPage()-1);
    }

    @Override
    public ItemStack[] getContent() {
        return new ItemStack[54];
    }

    @Override
    public void onClick(Inventory clickedInv, ItemStack clickedItem, ClickType clickType, InventoryAction action, int slot) {
        if (slot < 45) {
            boolean notHas = false;
            int price = 0;
            List<String> requirements = new ArrayList<>();
            SomCraftItem craftItem = getCraftRecipes().get(getOffset()+slot);
            for (SomItemStack itemStack : craftItem.getRecipe()) {
                int amount = itemStack.getAmount() * getAmount();
                if (getPlayerData().getItemInventory().hasContent(itemStack, amount)) {
                    requirements.add("§7・§e" + itemStack.getDisplay() + "§ax" + itemStack.getAmount() + " §b✔");
                } else {
                    requirements.add("§7・§e" + itemStack.getDisplay() + "§ax" + amount + " §c✖");
                    notHas = true;
                }
            }
            if (craftItem instanceof ShopItem shopItem) {
                int finalPrice = shopItem.getPrice()*getAmount();
                if (playerData.getMel() >= finalPrice) {
                    requirements.add("§7・§e" + finalPrice + "メル §b✔");
                    price = finalPrice;
                } else {
                    requirements.add("§7・§e" + finalPrice + "メル §c✖");
                    notHas = true;
                }
            }
            if (notHas) {
                getPlayerData().sendMessage(requirements, SomSound.Nope);
            } else {
                if (price != 0) playerData.removeMel(price);
                for (SomItemStack itemStack : craftItem.getRecipe()) {
                    playerData.getItemInventory().removeContent(itemStack);
                }
                SomItemStack itemStack = craftItem.getSomItemStack();
                getPlayerData().sendMessage("§e[" + itemStack.getDisplay() + "§ax" + itemStack.getAmount() * getAmount() + "]§aを§b" + getChar() + "§aしました", SomSound.LevelUp);
            }
        }
    }

    public String getChar() {
        return "作成";
    }

    public Map<Integer, SomCraftItem> getCraftRecipes() {
        return recipes;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

}
