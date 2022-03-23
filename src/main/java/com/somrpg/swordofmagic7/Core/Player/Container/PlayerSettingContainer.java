package com.somrpg.swordofmagic7.Core.Player.Container;

import com.somrpg.swordofmagic7.Core.Inventory.SomInventoryType;
import com.somrpg.swordofmagic7.Core.Player.Enum.*;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerSetting;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.configuration.file.FileConfiguration;

public class PlayerSettingContainer implements PlayerSetting {
    private final PlayerData playerData;

    PlayerSettingContainer(PlayerData playerData) {
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

    public PlayerData getPlayerData() {
        return playerData;
    }

    @Override
    public void setPlayerStrafMode(PlayerStrafeMode playerStrafeMode) {
        strafeMode = playerStrafeMode;
        getPlayerData().sendMessage("§bストレイフ条件§aを§e[" + playerStrafeMode.getDisplay() + "]§aに変更しました", SomSound.Tick);
    }

    @Override
    public PlayerStrafeMode getPlayerStrafeMode() {
        return strafeMode;
    }

    @Override
    public void setPlayerPvPMode(PlayerPvPMode playerPvPMode) {
        pvpMode = playerPvPMode;
        getPlayerData().sendMessage("§cPvP設定§aを§e[" + playerPvPMode.getDisplay() + "]§aに変更しました", SomSound.Tick);
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
    public void setPlayerCastMode(PlayerCastMode castMode) {
        this.castMode = castMode;
        getPlayerData().sendMessage("§bキャストモード§aを§e[" + castMode.getDisplay() + "]§aに変更しました", SomSound.Tick);
    }

    @Override
    public PlayerCastMode getPlayerCastMode() {
        return castMode;
    }

    @Override
    public void setDamageLog(DamageLog damageLog) {
        this.damageLog = damageLog;
        getPlayerData().sendMessage("§cダメージログ§aを§e[" + damageLog.getDisplay() + "]§aに変更しました", SomSound.Tick);
    }

    @Override
    public DamageLog getDamageLog() {
        return damageLog;
    }

    @Override
    public void setExpLog(ExpLog expLog) {
        this.expLog = expLog;
        getPlayerData().sendMessage("§e経験値ログ§aを§e[" + expLog.getDisplay() + "]§aに変更しました", SomSound.Tick);
    }

    @Override
    public ExpLog getExpLog() {
        return expLog;
    }

    @Override
    public void setDropLog(DropLog dropLog) {
        this.dropLog = dropLog;
        getPlayerData().sendMessage("§eドロップログ§aを§e[" + dropLog.getDisplay() + "]§aに変更しました", SomSound.Tick);
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
        if (log) getPlayerData().sendMessage("§bインベントリ表示§aを§e[" + viewInventory.getDisplay() + "]§aに変更しました", SomSound.Tick);
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

    private static final String PathCastMode = "Setting.CastMode";
    private static final String PathStrafeMode = "Setting.StrafeMode";
    private static final String PathDamageLog = "Setting.DamageLog";
    private static final String PathExpLog = "Setting.ExpLog";
    private static final String PathDropLog = "Setting.DropLog";
    private static final String PathViewDigit = "Setting.ViewDigit";

    @Override
    public void save(FileConfiguration data) {
        data.set(PathCastMode, getPlayerCastMode().toString());
        data.set(PathStrafeMode, getPlayerStrafeMode().toString());
        data.set(PathDamageLog, getDamageLog().toString());
        data.set(PathExpLog, getExpLog().toString());
        data.set(PathDropLog, getDropLog().toString());
        data.set(PathViewDigit, getViewDigit());
    }

    @Override
    public void load(FileConfiguration data) {
        castMode = PlayerCastMode.valueOf(data.getString(PathCastMode, getPlayerCastMode().toString()));
        strafeMode = PlayerStrafeMode.valueOf(data.getString(PathStrafeMode, getPlayerStrafeMode().toString()));
        damageLog = DamageLog.valueOf(data.getString(PathDamageLog, getDamageLog().toString()));
        expLog = ExpLog.valueOf(data.getString(PathExpLog, getExpLog().toString()));
        dropLog = DropLog.valueOf(data.getString(PathDropLog, getDropLog().toString()));
        viewDigit = data.getInt(PathViewDigit, getViewDigit());
    }
}
