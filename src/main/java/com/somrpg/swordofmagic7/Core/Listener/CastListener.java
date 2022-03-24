package com.somrpg.swordofmagic7.Core.Listener;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.EquipmentSlot;

public class CastListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = PlayerData.getData(player);
        Action action = event.getAction();
        if (playerData.isPlayMode() && player.getGameMode() != GameMode.SPECTATOR) {
            if (event.getHand() == EquipmentSlot.HAND) {
                switch (playerData.getPlayerCastMode()) {
                    case Hold -> {
                        int slot = player.getInventory().getHeldItemSlot();
                        if (action.isRightClick() && slot < 8) {
                            playerData.getSkillSlot().SkillSlotUse(slot);
                        }
                    }
                    case Renewed -> {

                    }
                    case Legacy -> {
                        if (action.isRightClick()) {
                            if (player.isSneaking()) {
                                playerData.getSkillSlot().SkillSlotUse(4);
                            } else {
                                playerData.getSkillSlot().SkillSlotUse(0);
                            }
                        } else if (action.isLeftClick()) {
                            if (player.isSneaking()) {
                                playerData.getSkillSlot().SkillSlotUse(3);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    void onToolChange(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = PlayerData.getData(player);
        if (playerData.isPlayMode()) {
            if (playerData.getPlayerCastMode().isRenewed() && event.getNewSlot() < 8) {
                int x = 0;
                if (player.isSneaking()) x += 8;
                if (playerData.isRightClickHold()) x += 16;
                player.getInventory().setHeldItemSlot(8);
                int slot = event.getNewSlot() + x;
                playerData.getSkillSlot().SkillSlotUse(slot);
            }
            if (!playerData.getPlayerCastMode().isHold()) {
                event.setCancelled(true);
                player.getInventory().setHeldItemSlot(8);
            }
        }
    }

    @EventHandler
    void onOffHandSwitch(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = PlayerData.getData(player);
        if (playerData.isPlayMode()) {
            if (playerData.getPlayerCastMode().isLegacy()) {
                if (player.isSneaking()) {
                    playerData.getSkillSlot().SkillSlotUse(5);
                } else {
                    playerData.getSkillSlot().SkillSlotUse(1);
                }
            }
            event.setCancelled(true);
        }
    }

    @EventHandler
    void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = PlayerData.getData(player);
        if (playerData.isPlayMode()) {
            if (playerData.getPlayerCastMode().isLegacy()) {
                if (player.isSneaking()) {
                    playerData.getSkillSlot().SkillSlotUse(6);
                } else {
                    playerData.getSkillSlot().SkillSlotUse(2);
                }
            }
            event.setCancelled(true);
        }
    }
}
