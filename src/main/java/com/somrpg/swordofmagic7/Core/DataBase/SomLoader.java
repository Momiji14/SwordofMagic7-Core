package com.somrpg.swordofmagic7.Core.DataBase;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Map.MapData;
import com.somrpg.swordofmagic7.Core.Map.TeleportGate.TeleportGateData;
import com.somrpg.swordofmagic7.Core.Map.WarpGate.WarpGateData;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public interface SomLoader {
    SomItemStack ErrorItemStack = new SomItemStack("Error SomItemStack", Material.BARRIER);
    Map<String, SomItemStack> SomItemDataList = new HashMap<>();
    Map<String, MapData> MapDataList = new HashMap<>();
    Map<String, WarpGateData> WarpGateList = new HashMap<>();
    Map<String, TeleportGateData> TeleportGateList = new HashMap<>();

    static SomItemStack getSomItemStack(String id) {
        if (SomItemDataList.containsKey(id)) {
            return SomItemDataList.get(id);
        }
        return null;
    }
}
