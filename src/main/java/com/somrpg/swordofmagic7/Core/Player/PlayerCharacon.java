package com.somrpg.swordofmagic7.Core.Player;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.SomCore;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class PlayerCharacon {

    private final PlayerData playerData;
    private final Player player;
    private int strafe = 2;
    private boolean wallKicked = false;
    private BukkitTask wallKickTask;

    PlayerCharacon(PlayerData playerData) {
        this.playerData = playerData;
        player = playerData.getPlayer();
        player.setAllowFlight(true);
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    public void resetStrafe() {
        strafe = 2;
    }

    public void wallKick() {
        Location loc = player.getLocation();
        loc.setPitch(0);
        Material type = loc.clone().add(loc.getDirection().setY(0.1).normalize()).getBlock().getType();
        if (!player.isFlying() && player.isSneaking() && !player.isOnGround() && type.isSolid() && type != Material.BARRIER) {
            player.setVelocity(loc.getDirection().normalize().multiply(-0.7).setY(0.6));
            wallKicked = true;
            resetStrafe();
            if (wallKickTask != null) wallKickTask.cancel();
            wallKickTask = SomCore.getSomTask().AsyncTaskLater(() -> wallKicked = false, 12);
        }
    }

    public void strafe() {
        if (!player.isFlying() && !player.isOnGround() && strafe > 0) {
            strafe--;
            double y;
            if (wallKicked) {
                y = 0.7;
                wallKicked = false;
                if (wallKickTask!= null) {
                    wallKickTask.cancel();
                    wallKickTask = null;
                }
            } else {
                y = player.getVelocity().getY();
                if (y < 0) y /= 3;
            }
            player.setVelocity(player.getLocation().getDirection().setY(0).normalize().multiply(0.7).setY(y));
        }
    }
}
