package com.somrpg.swordofmagic7.Core;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.SlimeSplitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;

public class Events implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = PlayerData.getData(player);
        playerData.load();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = PlayerData.getData(player);
        playerData.save();
    }

    @EventHandler
    public void onJump(PlayerJumpEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = PlayerData.getData(player);
        playerData.getPlayerCharacon().resetStrafe();
        player.setAllowFlight(playerData.getPlayerSetting().getPlayerStrafeMode().isDoubleJump());
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

    @EventHandler
    public void onSlimeSplit(SlimeSplitEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity attacker = event.getDamager();
        Entity victim = event.getEntity();
        if (attacker instanceof Player player) {
            PlayerData playerData = PlayerData.getData(player);
            if (playerData.getPlayerSetting().isPlayMode()) {
                event.setCancelled(true);
            }
        } else event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        PlayerData playerData = PlayerData.getData(player);
        if (playerData.getPlayerSetting().isPlayMode()) {
            playerData.getPlayerInput().onClickGUI(event);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        PlayerData playerData = PlayerData.getData(player);
        if (playerData.getPlayerSetting().isPlayMode()) {
            playerData.getPlayerInput().onCloseGUI(event);
        }
    }
}
