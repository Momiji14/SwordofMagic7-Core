package com.somrpg.swordofmagic7.Core.Menu;

import com.somrpg.swordofmagic7.Core.DataBase.DataBase;
import com.somrpg.swordofmagic7.Core.DataBase.TeleportGateLoader;
import com.somrpg.swordofmagic7.Core.Generic.BaseGraphicalUserInterface;
import com.somrpg.swordofmagic7.Core.Map.TeleportGate.TeleportGate;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import com.somrpg.swordofmagic7.Core.SomCore;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class TeleportGateMenu implements BaseGraphicalUserInterface {

    private final PlayerData playerData;

    public TeleportGateMenu(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public String getGUIDisplay() {
        return "§l転移門";
    }

    @Override
    public int getSize() {
        return 5;
    }

    @Override
    public ItemStack[] getContent() {
        ItemStack[] itemStack = new ItemStack[45];
        for (Map.Entry<Integer, String> gui : TeleportGateLoader.TeleportGateMenu.entrySet()) {
            if (gui.getKey() < 45) {
                itemStack[gui.getKey()] = DataBase.TeleportGateList.get(gui.getValue()).viewItemStack();
            } else {
                SomCore.log("TeleportGateGUIのスロット位置が範囲外です -> " + gui.getValue());
            }
        }
        return itemStack;
    }

    public void onClick(int slot) {
        TeleportGate teleport = DataBase.TeleportGateList.get(DataBase.TeleportGateList.get(slot));
        if (teleport.isDefaultActive() || getPlayerData().getActiveTeleportGate().contains(teleport.getId()) || getPlayer().hasPermission("som7.builder")) {
            getPlayerData().setMapData(teleport.getMapData());
            getPlayer().teleportAsync(teleport.getLocation());
            getPlayer().showTitle(Title.title(Component.text(teleport.getTitle()), Component.text(teleport.getSubTitle())));
            SomSound.LevelUp.play(getPlayer());
        } else {
            getPlayerData().sendMessage("§e[転移門]§aが§b[有効化]§aされていません", SomSound.Nope);
        }
    }

    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }
}
