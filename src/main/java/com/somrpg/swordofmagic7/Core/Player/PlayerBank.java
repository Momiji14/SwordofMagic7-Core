package com.somrpg.swordofmagic7.Core.Player;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerBankInterface;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerDataInterface;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;

public class PlayerBank implements PlayerBankInterface {

    private final PlayerData playerData;
    private int Mel = 10000;

    PlayerBank(PlayerData playerData) {
        this.playerData = playerData;
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
