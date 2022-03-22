package com.somrpg.swordofmagic7.Core.Listener;

import com.destroystokyo.paper.event.player.PlayerRecipeBookClickEvent;
import com.somrpg.swordofmagic7.Core.SomCore;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTakeLecternBookEvent;
import org.bukkit.util.Vector;

public class PhysicsListener implements Listener {

    @EventHandler
    public void onEntityInteract(EntityInteractEvent event) {
        Block block = event.getBlock();
        if(block.getType() == Material.FARMLAND) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    void onPlayerLectern(PlayerTakeLecternBookEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    void onRecipeBookClick(PlayerRecipeBookClickEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPressurePlate(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction().equals(Action.PHYSICAL)) {
            if (event.getClickedBlock().getType() == Material.STONE_PRESSURE_PLATE) {
                Material Under1 = event.getClickedBlock().getLocation().add(0, -1,0).getBlock().getType();
                Material Under2 = event.getClickedBlock().getLocation().add(0, -2,0).getBlock().getType();
                if (Under1 == Material.IRON_BLOCK) {
                    SomSound.Shoot.play(player);
                    player.setVelocity(player.getLocation().getDirection().multiply(0.4).setY(0.5));
                } else if (Under1 == Material.GOLD_BLOCK || Under2 == Material.GOLD_BLOCK) {
                    event.setCancelled(true);
                    player.setGravity(false);
                    SomCore.getSomTask().AsyncTask(() -> {
                        double y = 1;
                        while (y > -1 && !player.isSneaking()) {
                            y -= 0.08;
                            player.setVelocity(player.getLocation().getDirection().multiply(2).setY(y));
                            SomCore.getSomTask().sleepTick(1);
                        }
                        player.setGravity(true);
                    });
                    SomSound.Shoot.play(player);

                } else if (Under1 == Material.EMERALD_BLOCK || Under2 == Material.EMERALD_BLOCK) {
                    event.setCancelled(true);
                    Vector vec = new Vector(0, 2.8, 0);
                    player.setVelocity(vec);
                    SomSound.Shoot.play(player);
                }
            }
        }
    }
}
