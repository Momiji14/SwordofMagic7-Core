package com.somrpg.swordofmagic7.Core.Production.Base;

import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Production.CheckReturn;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class SomCraftDisplayContainer implements SomCraftDisplay {
    private String display;
    private ItemStack[] content = new ItemStack[54];
    private Map<Integer, CraftSlot> craftSlots;
    private int amount = 1;
    private int page = 1;
    private int maxPage = 1;
    private final PlayerData playerData;

    public SomCraftDisplayContainer(PlayerData playerData) {
        this.playerData = playerData;
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

    @Override
    public void open(CraftData craftData) {
        display = craftData.getDisplay();
        craftSlots = craftData.getCraftSlots();
        content = new ItemStack[54];
        for (int slot = 0; slot < 45; slot++) {
            int index = getOffset()+slot;
            if (craftData.getCraftSlots().containsKey(index)) {
                content[slot] = craftData.getCraftSlots().get(index).getCraftItem().viewItemStack();
            }
        }
        content[46] = SomCraftDisplay.CraftGUIAmountChangeIcon(-100);
        content[47] = SomCraftDisplay.CraftGUIAmountChangeIcon(-10);
        content[48] = SomCraftDisplay.CraftGUIAmountChangeIcon(-1);
        content[50] = SomCraftDisplay.CraftGUIAmountChangeIcon(1);
        content[51] = SomCraftDisplay.CraftGUIAmountChangeIcon(10);
        content[52] = SomCraftDisplay.CraftGUIAmountChangeIcon(100);
        openGUI();
    }

    @Override
    public int getRawSize() {
        return getSize()*9-9;
    }

    @Override
    public int getOffset() {
        return getRawSize()*(getPage()-1);
    }

    @Override
    public ItemStack[] getContent() {
        content[45] = getPage() > 0 ? SomCraftDisplay.PreviousPage : GenericConfig.GUIPartition;
        content[49] = SomCraftDisplay.CraftGUIAmountIcon(getAmount());
        content[53] = maxPage > getPage() ? SomCraftDisplay.NextPage : GenericConfig.GUIPartition;
        return content;
    }

    @Override
    public CheckReturn isCraftAble(SomCraftItem craftItem, CheckReturn checkReturn) {
        for (SomItemStack itemStack : craftItem.getRecipe().getReqStack()) {
            int amount = itemStack.getAmount() * getAmount();
            if (getPlayerData().getItemInventory().hasContent(itemStack, amount)) {
                checkReturn.getRequirements().add("§7・§e" + itemStack.getDisplay() + "§ax" + itemStack.getAmount() + " §b✔");
            } else {
                checkReturn.getRequirements().add("§7・§e" + itemStack.getDisplay() + "§ax" + amount + " §c✖");
                checkReturn.setOK(false);
            }
        }
        return checkReturn;
    }

    @Override
    public void craft(SomCraftItem craftItem, CheckReturn checkReturn) {
        if (checkReturn.isOK()) {
            for (SomItemStack itemStack : craftItem.getRecipe().getReqStack()) {
                playerData.getItemInventory().removeContent(itemStack);
            }
            SomItemStack itemStack = craftItem.getSomItemStack();
            getPlayerData().sendMessage("§e[" + itemStack.getDisplay() + "§ax" + itemStack.getAmount() * getAmount() + "§e]§aを§b" + getChar() + "§aしました", SomSound.LevelUp);
        } else {
            getPlayerData().sendMessage(checkReturn.getRequirements(), SomSound.Nope);
        }
    }

    @Override
    public Map<Integer, CraftSlot> getCraftSlots() {
        return craftSlots;
    }

    @Override
    public void onClick(Inventory clickedInv, ItemStack clickedItem, ClickType clickType, InventoryAction action, int slot) {
        if (slot < 45) {
            SomCraftItem craftItem = getCraftSlots().get(getOffset()+slot).getCraftItem();
            onClickProduct(craftItem);
        }
    }

    @Override
    public void onClickProduct(SomCraftItem craftItem) {
        CheckReturn checkReturn = new CheckReturn();
        if (isCraftAble(craftItem, checkReturn).isOK()) {
            craft(craftItem, checkReturn);
        }
    }

    @Override
    public String getChar() {
        return "作成";
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    @Override
    public int getMaxPage() {
        return maxPage;
    }

}
