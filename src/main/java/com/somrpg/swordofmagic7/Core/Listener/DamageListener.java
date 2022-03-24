package com.somrpg.swordofmagic7.Core.Listener;

import com.somrpg.swordofmagic7.Core.Entity.Eemey.EnemyController;
import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    @EventHandler
    void onDamage(EntityDamageEvent event) {
        Entity victim = event.getEntity();
        if (victim instanceof Player player) {
            event.setCancelled(true);
            PlayerData playerData = PlayerData.getData(player);
        } else if (EnemyController.EnemyControllerList.containsKey(victim)) {
            event.setCancelled(true);
        }
        switch (event.getCause()) {
            case FALL, HOT_FLOOR, FIRE_TICK -> {
                victim.setFireTicks(0);
                event.setCancelled(true);
                return;
            }
            case LAVA, DROWNING -> {
                if (victim instanceof Player player) {
                    PlayerData playerData = PlayerData.getData(player);
                    playerData.addHealth(-playerData.getMaxHealth()/10);
                }
            }
            case VOID -> {
                if (victim instanceof Player player) {
                    GenericConfig.spawnPlayer(player);
                }
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity attacker = event.getDamager();
        Entity victim = event.getEntity();

    }

}
