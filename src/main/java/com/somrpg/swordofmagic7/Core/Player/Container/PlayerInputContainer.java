package com.somrpg.swordofmagic7.Core.Player.Container;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerInput;
import com.somrpg.swordofmagic7.Core.SomCore;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class PlayerInputContainer implements PlayerInput {

    private final PlayerData playerData;

    PlayerInputContainer(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }

    @Override
    public PlayerInputContainer getPlayerInputContainer() {
        return this;
    }

    private boolean RightClickHold = false;
    private BukkitTask RightClickHoldTask;

    @Override
    public boolean isRightClickHold() {
        return isRightClickHold();
    }

    @Override
    public void setRightClickHold() {
        RightClickHold = true;
        if (getPlayerData().getPlayerCastMode().isRenewed()) getPlayerData().getSkillSlot().viewBottom();
        setRightClickHoldTask();
    }

    void setRightClickHoldTask() {
        if (RightClickHoldTask != null) RightClickHoldTask.cancel();
        RightClickHoldTask = SomCore.getSomTask().AsyncTaskLater(() -> {
            RightClickHold = false;
            if (getPlayerData().getPlayerCastMode().isRenewed()) getPlayerData().getSkillSlot().viewBottom();
        }, 5);
    }
}
