package com.somrpg.swordofmagic7.Core.Listener;

import com.somrpg.swordofmagic7.Core.DataBase.DataBase;
import com.somrpg.swordofmagic7.Core.Generic.DecoContent;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class InteractListener implements Listener {

    @EventHandler
    void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = PlayerData.getData(player);
        Entity entity = event.getRightClicked();
        if (playerData.isPlayMode() && event.getHand() == org.bukkit.inventory.EquipmentSlot.HAND && entity.getCustomName() != null) {
            event.setCancelled(true);
            NPCRegistry npcRegistry = CitizensAPI.getNPCRegistry();
            if (npcRegistry.isNPC(entity)) {
                NPC npc = npcRegistry.getNPC(entity);
                String shop = DecoContent.uncolored(entity.getCustomName());
                if (DataBase.ShopDataList.containsKey(shop)) {
                    playerData.getShopDisplay().open(DataBase.getShopData(shop));
                }
            }
        }
    }
}
