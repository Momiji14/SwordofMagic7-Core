package com.somrpg.swordofmagic7.Core.Listener;

import com.somrpg.swordofmagic7.Core.Entity.Eemey.EnemyController;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity attacker = event.getDamager();
        Entity victim = event.getEntity();
        if (victim instanceof Player player) {
            event.setCancelled(true);
            PlayerData playerData = PlayerData.getData(player);
        } else if (EnemyController.EnemyControllerList.containsKey(victim)) {
            event.setCancelled(true);
        }
    }

}
