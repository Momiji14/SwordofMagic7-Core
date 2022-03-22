package com.somrpg.swordofmagic7.Core.Menu;

import com.somrpg.swordofmagic7.Core.DataBase.SomLoader;
import com.somrpg.swordofmagic7.Core.Generic.BaseGraphicalUserInterface;
import com.somrpg.swordofmagic7.Core.Generic.DecoContent;
import com.somrpg.swordofmagic7.Core.Map.TeleportGate.TeleportGate;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import com.somrpg.swordofmagic7.Core.SomCore;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public interface TeleportGateMenu extends TeleportGate, BaseGraphicalUserInterface {

    Map<Integer, String> TeleportGateMenuSlot = new HashMap<>();

    @Override
    default String getGUIDisplay() {
        return "§lテレポート";
    }

    @Override
    default int getSize() {
        return 1;
    }

    @Override
    default ItemStack[] getContent() {
        ItemStack[] itemStack = new ItemStack[45];
        for (Map.Entry<Integer, String> gui : TeleportGateMenuSlot.entrySet()) {
            if (gui.getKey() < 45) {
                itemStack[gui.getKey()] = SomLoader.TeleportGateList.get(gui.getValue()).viewItemStack();
            } else {
                SomCore.log("TeleportGateGUIのスロット位置が範囲外です -> " + gui.getValue());
            }
        }
        return itemStack;
    }

    default void onClick(int slot) {
        TeleportGate teleport = SomLoader.TeleportGateList.get(TeleportGateMenuSlot.get(slot));
        if (getPlayerData().getActiveTeleportGate().contains(teleport.getId()) || getPlayer().hasPermission("som7.builder")) {
            getPlayerData().setMapData(teleport.getMapData());
            getPlayer().teleportAsync(teleport.getLocation());
            getPlayer().showTitle(Title.title(Component.text(getTitle()), Component.text(getSubTitle())));
            SomSound.LevelUp.play(getPlayer());
        } else {
            getPlayerData().sendMessage("§e[転移門]§aが§b[有効化]§aされていません", SomSound.Nope);
        }
    }
}
