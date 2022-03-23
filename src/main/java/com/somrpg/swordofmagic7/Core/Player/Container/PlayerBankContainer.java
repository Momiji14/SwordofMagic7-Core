package com.somrpg.swordofmagic7.Core.Player.Container;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerBank;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;

public class PlayerBankContainer implements PlayerBank {

    private final PlayerData playerData;
    private int Mel = 10000;

    PlayerBankContainer(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public PlayerBankContainer getPlayerBankContainer() {
        return this;
    }

    @Override
    public void setMel(int mel) {
        Mel = mel;
    }

    @Override
    public void addMel(int mel) {
        Mel += mel;
        playerData.sendMessage("§b[+]§e" + mel + "メル", SomSound.Tick);
    }

    @Override
    public void removeMel(int mel) {
        Mel -= mel;
        playerData.sendMessage("§c[-]§e" + mel + "メル", SomSound.Tick);
    }

    @Override
    public int getMel() {
        return Mel;
    }
}
