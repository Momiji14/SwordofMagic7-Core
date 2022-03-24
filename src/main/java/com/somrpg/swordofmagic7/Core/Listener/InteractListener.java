package com.somrpg.swordofmagic7.Core.Listener;

import com.somrpg.swordofmagic7.Core.DataBase.ShopDataLoader;
import com.somrpg.swordofmagic7.Core.Generic.DecoContent;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.Production.ShopData;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener {

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = PlayerData.getData(player);
        Entity entity = event.getRightClicked();
        if (playerData.isPlayMode() && event.getHand() == org.bukkit.inventory.EquipmentSlot.HAND && entity.getCustomName() != null) {
            event.setCancelled(true);
            NPCRegistry npcRegistry = CitizensAPI.getNPCRegistry();
            if (npcRegistry.isNPC(entity)) {
                NPC npc = npcRegistry.getNPC(entity);
                String shop = DecoContent.uncolored(entity.getCustomName());
                ShopData shopData = ShopDataLoader.getShopData(shop);
                if (shopData != null) {
                    playerData.getShopDisplay().open(shopData);
                } else if (shop.equalsIgnoreCase("転職神官")) {
                    playerData.getClassMenu().resetSelectSlot();
                    playerData.getClassMenu().openGUI();
                }
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if (player.getGameMode() != GameMode.CREATIVE) {
            if (block != null) {
                if (block.getType() != Material.LECTERN) {
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
            }
        } else if (event.getAction() == Action.PHYSICAL) {
            if (block != null && block.getType() == Material.FARMLAND) {
                event.setCancelled(true);
            }
        }
    }
}
