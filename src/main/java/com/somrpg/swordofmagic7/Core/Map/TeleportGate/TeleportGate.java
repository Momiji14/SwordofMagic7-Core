package com.somrpg.swordofmagic7.Core.Map.TeleportGate;

import com.somrpg.swordofmagic7.Core.DataBase.SomLoader;
import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.ViewableItemStack;
import com.somrpg.swordofmagic7.Core.Map.MapData;
import com.somrpg.swordofmagic7.Core.Particle.ParticleData;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import com.somrpg.swordofmagic7.Core.SomCore;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;

public interface TeleportGate {

    String getId();
    String getDisplay();
    Material getMaterial();
    String getTitle();
    String getSubTitle();
    Location getLocation();
    MapData getMapData();
    boolean isDefaultActive();

    default ItemStack view() {
        return ViewableItemStack.create(getDisplay(), getMaterial()).viewItemStack();
    }

    default void Selector(Player player) {
        PlayerData playerData = PlayerData.getData(player);
        Location pLoc = player.getLocation();
        for (Map.Entry<String, TeleportGateData> entry : SomLoader.TeleportGateList.entrySet()) {
            TeleportGateData teleport = entry.getValue();
            if (teleport.getLocation().distance(pLoc) < 2) {
                if (!teleport.isDefaultActive() && !playerData.getActiveTeleportGate().contains(teleport.getId())) {
                    playerData.getActiveTeleportGate().add(teleport.getId());
                    player.sendMessage("§e[" + teleport.getDisplay() + "]§aを§b[有効化]§aしました");
                    SomSound.LevelUp.play(player);
                }
            }
        }
    }

    default void start() {
        ParticleData particleData = new ParticleData(Particle.FIREWORKS_SPARK).setSpeed(0.1f).setVector(GenericConfig.VectorUp);
        World world = getLocation().getWorld();
        new BukkitRunnable() {
            int i = 0;
            final double increment = (2 * Math.PI) / 90;
            final double radius = 2;
            @Override
            public void run() {
                double angle = i * increment;
                double x = radius * Math.cos(angle);
                double z = radius * Math.sin(angle);
                Location nLoc = new Location(world, getLocation().getX() + x, getLocation().getY(), getLocation().getZ() + z);
                Location nLoc2 = new Location(world, getLocation().getX() - x, getLocation().getY(), getLocation().getZ() - z);
                particleData.spawn(nLoc);
                particleData.spawn(nLoc2);
                i++;
            }
        }.runTaskTimerAsynchronously(SomCore.getPlugin(), 0, 1);
    }
}
