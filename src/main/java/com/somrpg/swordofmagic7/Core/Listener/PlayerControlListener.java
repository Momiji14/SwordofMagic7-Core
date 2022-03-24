package com.somrpg.swordofmagic7.Core.Listener;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.somrpg.swordofmagic7.Core.Map.TeleportGate.TeleportGate;
import com.somrpg.swordofmagic7.Core.Map.WarpGate.WarpGate;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;

public class PlayerControlListener implements Listener {

    @EventHandler
    public void onJump(PlayerJumpEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = PlayerData.getData(player);
        if (playerData.isPlayMode()) {
            playerData.getPlayerCharacon().resetStrafe();
            player.setAllowFlight(playerData.getPlayerSetting().getPlayerStrafeMode().isDoubleJump());
        }
    }

    @EventHandler
    public void onSprintToggle(PlayerToggleSprintEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = PlayerData.getData(player);
        if (!player.isSprinting() && playerData.getPlayerSetting().getPlayerStrafeMode().isAirDash()) {
            playerData.getPlayerCharacon().strafe();
        }
    }

    @EventHandler
    public void onSneakToggle(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = PlayerData.getData(player);
        if (player.isSneaking()) {
            playerData.getPlayerCharacon().wallKick();
            WarpGate.Selector(player);
            TeleportGate.Selector(player);
        }
    }

    @EventHandler
    void onFlightToggle(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE) {
            PlayerData playerData = PlayerData.getData(player);
            event.setCancelled(true);
            player.setFlying(false);
            if (!player.isFlying() && playerData.getPlayerSetting().getPlayerStrafeMode().isDoubleJump()) {
                playerData.getPlayerCharacon().strafe();
            }
        }
    }

}
