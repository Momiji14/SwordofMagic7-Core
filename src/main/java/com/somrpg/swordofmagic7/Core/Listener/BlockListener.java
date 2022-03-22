package com.somrpg.swordofmagic7.Core.Listener;

import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class BlockListener implements Listener {

    @EventHandler
    void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (!player.hasPermission("som7.builder") || PlayerData.getData(player).isPlayMode()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    void onBlockDamage(BlockDamageEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (player.getGameMode() != GameMode.CREATIVE) {
            event.setInstaBreak(false);
        }
    }

    @EventHandler
    void onBlockExplode(BlockExplodeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    void onExplosionPrime(ExplosionPrimeEvent event) {
        event.setCancelled(true);
    }


}
