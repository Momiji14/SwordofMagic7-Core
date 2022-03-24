package com.somrpg.swordofmagic7.Core.Player.Menu;

import com.somrpg.swordofmagic7.Core.DataBase.TeleportGateLoader;
import com.somrpg.swordofmagic7.Core.Map.TeleportGate.TeleportGate;
import com.somrpg.swordofmagic7.Core.SomCore;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public interface TeleportGateMenu extends BaseMenu {

    String display = "転移門";

    @Override
    default String getGUIDisplay() {
        return display;
    }

    @Override
    default int getSize() {
        return 5;
    }

    @Override
    default ItemStack[] getContent() {
        ItemStack[] itemStack = new ItemStack[45];
        for (Map.Entry<Integer, String> gui : TeleportGateLoader.TeleportGateMenu.entrySet()) {
            if (gui.getKey() < 45) {
                itemStack[gui.getKey()] = TeleportGateLoader.getTeleportGate(gui.getValue()).viewItemStack();
            } else {
                SomCore.log("TeleportGateGUIのスロット位置が範囲外です -> " + gui.getValue());
            }
        }
        return itemStack;
    }

    @Override
    default void onClick(Inventory clickedInv, ItemStack clickedItem, ClickType clickType, InventoryAction action, int slot) {
        TeleportGate teleport = TeleportGateLoader.getTeleportGate(TeleportGateLoader.TeleportGateMenu.get(slot));
        assert teleport != null;
        if (teleport.isDefaultActive() || getPlayerData().getActiveTeleportGate().contains(teleport.getId()) || getPlayer().hasPermission("som7.builder")) {
            getPlayerData().setMapData(teleport.getMapData());
            getPlayer().teleportAsync(teleport.getLocation());
            getPlayer().showTitle(Title.title(Component.text(teleport.getTitle()), Component.text(teleport.getSubTitle())));
            SomSound.LevelUp.play(getPlayer());
        } else {
            getPlayerData().sendMessage("§e[転移門]§aが§b[有効化]§aされていません", SomSound.Nope);
        }
    }
}
