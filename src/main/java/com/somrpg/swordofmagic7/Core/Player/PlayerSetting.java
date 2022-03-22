package com.somrpg.swordofmagic7.Core.Player;

import com.somrpg.swordofmagic7.Core.Inventory.SomInventoryType;
import com.somrpg.swordofmagic7.Core.Player.Enum.*;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerDataInterface;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerSettingInterface;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;

public class PlayerSetting implements PlayerDataInterface, PlayerSettingInterface {
    private final PlayerData playerData;

    PlayerSetting(PlayerData playerData) {
        this.playerData = playerData;
    }

    private PlayerStrafeMode strafeMode = PlayerStrafeMode.DoubleJump;
    private PlayerPvPMode pvpMode = PlayerPvPMode.Friendly;
    private boolean playMode = true;
    private PlayerCastMode castMode = PlayerCastMode.Hold;
    private int viewDigit = 0;

    private DamageLog damageLog = DamageLog.Disable;
    private ExpLog expLog = ExpLog.Disable;
    private DropLog dropLog = DropLog.Disable;

    private SomInventoryType viewInventory = SomInventoryType.ItemInventory;

    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }

    @Override
    public void setPlayerStrafMode(PlayerStrafeMode playerStrafeMode) {
        strafeMode = playerStrafeMode;
        sendMessage("§bストレイフ条件§aを§e[" + playerStrafeMode.getDisplay() + "]§aに変更しました", SomSound.Tick);
    }

    @Override
    public PlayerStrafeMode getPlayerStrafeMode() {
        return strafeMode;
    }

    @Override
    public void setPlayerPvPMode(PlayerPvPMode playerPvPMode) {
        pvpMode = playerPvPMode;
        sendMessage("§cPvP設定§aを§e[" + playerPvPMode.getDisplay() + "]§aに変更しました", SomSound.Tick);
    }

    @Override
    public PlayerPvPMode getPlayerPvPMode() {
        return pvpMode;
    }

    @Override
    public void setPlayMode(boolean playMode) {
        this.playMode = playMode;
    }

    @Override
    public boolean isPlayMode() {
        return playMode;
    }

    @Override
    public void setCastMode(PlayerCastMode castMode) {
        this.castMode = castMode;
        sendMessage("§bキャストモード§aを§e[" + castMode.getDisplay() + "]§aに変更しました", SomSound.Tick);
    }

    @Override
    public PlayerCastMode getCastMode() {
        return castMode;
    }

    @Override
    public void setDamageLog(DamageLog damageLog) {
        this.damageLog = damageLog;
        sendMessage("§cダメージログ§aを§e[" + damageLog.getDisplay() + "]§aに変更しました", SomSound.Tick);
    }

    @Override
    public DamageLog getDamageLog() {
        return damageLog;
    }

    @Override
    public void setExpLog(ExpLog expLog) {
        this.expLog = expLog;
        sendMessage("§e経験値ログ§aを§e[" + expLog.getDisplay() + "]§aに変更しました", SomSound.Tick);
    }

    @Override
    public ExpLog getExpLog() {
        return expLog;
    }

    @Override
    public void setDropLog(DropLog dropLog) {
        this.dropLog = dropLog;
        sendMessage("§eドロップログ§aを§e[" + dropLog.getDisplay() + "]§aに変更しました", SomSound.Tick);
    }

    @Override
    public DropLog getDropLog() {
        return dropLog;
    }

    @Override
    public void setViewInventory(SomInventoryType type) {
        setViewInventory(type, true);
    }

    public void setViewInventory(SomInventoryType type, boolean log) {
        viewInventory = type;
        getPlayerData().viewUpdate();
        if (log) sendMessage("§bインベントリ表示§aを§e[" + viewInventory.getDisplay() + "]§aに変更しました", SomSound.Tick);
    }

    @Override
    public SomInventoryType getViewInventory() {
        return viewInventory;
    }

    @Override
    public void setViewDigit(int digit) {
        viewDigit = digit;
    }

    @Override
    public int getViewDigit() {
        return viewDigit;
    }
}
