package com.somrpg.swordofmagic7.Core.Player;

import com.somrpg.swordofmagic7.Core.Generic.Parameter.StatusParameterInterface;
import com.somrpg.swordofmagic7.Core.Inventory.*;
import com.somrpg.swordofmagic7.Core.Map.MapData;
import com.somrpg.swordofmagic7.Core.Map.MapDataInterface;
import com.somrpg.swordofmagic7.Core.Menu.PlayerUserMenu;
import com.somrpg.swordofmagic7.Core.Menu.TeleportGateMenu;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerBankInterface;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerEntityInterface;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerInput;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerSettingInterface;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerViewUpdate;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.entity.Player;

import java.util.List;

public interface PlayerData extends StatusParameterInterface, PlayerEntityInterface, PlayerSettingInterface, PlayerBankInterface, PlayerViewUpdate {

    static PlayerData getData(Player player) {
        return PlayerDataContainer.getData(player);
    }

    PlayerData getPlayerData();

    default PlayerDataContainer getPlayerDataContainer() {
        return getPlayerData().getPlayerDataContainer();
    }

    default Player getPlayer() {
        return getPlayerData().getPlayer();
    }

    default void sendMessage(String message, SomSound sound) {
        sendMessage(List.of(message.split("\n")), sound);
    }

    default void sendMessage(List<String> message, SomSound sound) {
        Player player = getPlayer();
        for (String msg : message) {
            player.sendMessage(msg);
        }
        sound.play(player);
    }

    default BaseInventory getBaseViewInventory() {
        return getPlayerData().getBaseInventory(getViewInventory());
    }

    default SomInventoryType getViewInventory() {
        return getPlayerSetting().getViewInventory();
    }

    default PlayerEntity getPlayerEntity() {
        return getPlayerData().getPlayerEntity();
    }

    default PlayerCharacon getPlayerCharacon() {
        return getPlayerData().getPlayerCharacon();
    }

    default com.somrpg.swordofmagic7.Core.Player.Interface.PlayerViewBar getPlayerViewBar() {
        return getPlayerData().getPlayerViewBar();
    }

    default PlayerInput getPlayerInput() {
        return getPlayerData().getPlayerInput();
    }

    default PlayerStatistics getPlayerStatistics() {
        return getPlayerData().getPlayerStatistics();
    }

    default PlayerBank getPlayerBank() {
        return getPlayerData().getPlayerBank();
    }

    default PlayerSetting getPlayerSetting() {
        return getPlayerData().getPlayerSetting();
    }

    default ItemInventory getItemInventory() {
        return getPlayerData().getItemInventory();
    }

    default RuneInventory getRuneInventory() {
        return getPlayerData().getRuneInventory();
    }

    default PetInventory getPetInventory() {
        return getPlayerData().getPetInventory();
    }


    default PlayerUserMenu getUserMenu() {
        return getPlayerData().getUserMenu();
    }
    default PlayerUserMenu getSettingMenu() {
        return getPlayerData().getSettingMenu();
    }
    default TeleportGateMenu getTeleportGateMenu() {
        return getPlayerData().getTeleportGateMenu();
    }

    default BaseInventory getBaseInventory(SomInventoryType type) {
        switch (type) {
            case ItemInventory -> {
                return getItemInventory();
            }
            case RuneInventory -> {
                return getRuneInventory();
            }
            case PetInventory -> {
                return getPetInventory();
            }
        }
        return null;
    }

    default void setMapData(MapData mapData) {
        getPlayerData().setMapData(mapData);
    }

    default MapDataInterface getMapData() {
        return getPlayerData().getMapData();
    }

    default List<String> getActiveTeleportGate() {
        return getPlayerData().getActiveTeleportGate();
    }

    default void save() {
        getPlayerDataContainer().save();
    }

    default void load() {
        getPlayerDataContainer().load();
    }

    @Override
    default void setMel(int mel) {
        getPlayerBank().setMel(mel);
    }

    @Override
    default void addMel(int mel) {
        getPlayerBank().addMel(mel);
    }

    @Override
    default void removeMel(int mel) {
        getPlayerBank().removeMel(mel);
    }

    @Override
    default int getMel() {
        return getPlayerBank().getMel();
    }

    @Override
    default void setMaxHealth(double maxHealth) {
        getPlayerEntity().setMaxHealth(maxHealth);
    }

    @Override
    default double getMaxHealth() {
        return getPlayerEntity().getMaxHealth();
    }

    default void setHealth(double health) {
        getPlayerEntity().setHealth(health);
    }

    default double getHealth() {
        return getPlayerEntity().getHealth();
    }

    @Override
    default void setHealthRegen(double healthRegen) {
        getPlayerEntity().setHealthRegen(healthRegen);
    }

    @Override
    default double getHealthRegen() {
        return getPlayerEntity().getHealthRegen();
    }

    @Override
    default void setMaxMana(double maxMana) {
        getPlayerEntity().setMaxMana(maxMana);
    }

    @Override
    default double getMaxMana() {
        return getPlayerEntity().getMaxMana();
    }

    default void setMana(double mana) {
        getPlayerEntity().setMana(mana);
    }

    default double getMana() {
        return getPlayerEntity().getMana();
    }

    @Override
    default void setManaRegen(double manaRegen) {
        getPlayerEntity().setManaRegen(manaRegen);
    }

    @Override
    default double getManaRegen() {
        return getPlayerEntity().getManaRegen();
    }

    @Override
    default void setATK(double ATK) {
        getPlayerEntity().setATK(ATK);
    }

    @Override
    default double getATK() {
        return getPlayerEntity().getATK();
    }

    @Override
    default void setDEF(double DEF) {
        getPlayerEntity().setDEF(DEF);
    }

    @Override
    default double getDEF() {
        return getPlayerEntity().getDEF();
    }

    @Override
    default void setACC(double ACC) {
        getPlayerEntity().setACC(ACC);
    }

    @Override
    default double getACC() {
        return getPlayerEntity().getACC();
    }

    @Override
    default void setEVA(double EVA) {
        getPlayerEntity().setEVA(EVA);
    }

    @Override
    default double getEVA() {
        return getPlayerEntity().getEVA();
    }

    @Override
    default void setCriticalRate(double criticalRate) {
        getPlayerEntity().setCriticalRate(criticalRate);
    }

    @Override
    default double getCriticalRate() {
        return getPlayerEntity().getCriticalRate();
    }

    @Override
    default void setCriticalResist(double criticalResist) {
        getPlayerEntity().setCriticalResist(criticalResist);
    }

    @Override
    default double getCriticalResist() {
        return getPlayerEntity().getCriticalResist();
    }
}
