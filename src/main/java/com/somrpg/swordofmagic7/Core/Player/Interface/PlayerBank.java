package com.somrpg.swordofmagic7.Core.Player.Interface;

import com.somrpg.swordofmagic7.Core.Player.Container.PlayerBankContainer;

public interface PlayerBank {

    PlayerBankContainer getPlayerBankContainer();

    default void setMel(int mel) {
        getPlayerBankContainer().setMel(mel);
    }
    default void addMel(int mel) {
        getPlayerBankContainer().addMel(mel);
    }
    default void removeMel(int mel) {
        getPlayerBankContainer().removeMel(mel);
    }
    default int getMel() {
        return getPlayerBankContainer().getMel();
    }

}
