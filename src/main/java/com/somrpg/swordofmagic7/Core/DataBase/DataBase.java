package com.somrpg.swordofmagic7.Core.DataBase;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Map.MapData;
import com.somrpg.swordofmagic7.Core.Map.TeleportGate.TeleportGate;
import com.somrpg.swordofmagic7.Core.Map.TeleportGate.TeleportGateData;
import com.somrpg.swordofmagic7.Core.Map.WarpGate.WarpGate;
import com.somrpg.swordofmagic7.Core.Map.WarpGate.WarpGateData;
import com.somrpg.swordofmagic7.Core.Production.*;
import com.somrpg.swordofmagic7.Core.Production.Base.SomRecipe;
import com.somrpg.swordofmagic7.Core.SomCore;
import org.bukkit.Material;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DataBase {

    SomItemStack ErrorItemStack = new SomItemStack("Error SomItemStack", Material.BARRIER);
    Map<String, SomItemStack> SomItemDataList = new HashMap<>();
    Map<String, SomRecipe> SomRecipeList = new HashMap<>();
    Map<String, ShopData> ShopDataList = new HashMap<>();
    Map<String, MapData> MapDataList = new HashMap<>();
    Map<String, WarpGateData> WarpGateList = new HashMap<>();
    Map<String, TeleportGateData> TeleportGateList = new HashMap<>();

    static void loadError(@NonNull File file) {
        SomCore.log("§cLoading Error -> " + file.getName());
    }

    static List<File> dump(@NonNull File file) {
        List<File> list = new ArrayList<>();
        File[] flies = file.listFiles();
        if (flies != null) {
            for (File tmpFile : flies) {
                if (!tmpFile.getName().equals(".sync")) {
                    if (tmpFile.isDirectory()) {
                        list.addAll(dump(tmpFile));
                    } else {
                        list.add(tmpFile);
                    }
                }
            }
        } else {
            SomCore.log("NullFile -> " + file.getPath());
        }
        return list;
    }

    static void dataLoadable() {
        SomItemDataLoader.load();
        MapDataLoader.load();
        RecipeLoader.load();
        ShopDataLoader.load();
    }

    static void singleLoad() {
        WarpGateLoader.load();
        TeleportGateLoader.load();
    }

    @Nullable
    static SomItemStack getSomItemStack(String key) {
        if (SomItemDataList.containsKey(key)) {
            return SomItemDataList.get(key).clone();
        }
        return null;
    }

    @Nullable
    static MapData getMapData(String key) {
        if (MapDataList.containsKey(key)) {
            return MapDataList.get(key);
        }
        return null;
    }

    @Nullable
    static WarpGate getWarpGate(String key) {
        if (WarpGateList.containsKey(key)) {
            return WarpGateList.get(key);
        }
        return null;
    }

    @Nullable
    static TeleportGate getTeleportGate(String key) {
        if (TeleportGateList.containsKey(key)) {
            return TeleportGateList.get(key);
        }
        return null;
    }

    @Nullable
    static ShopData getShopData(String key) {
        if (ShopDataList.containsKey(key)) {
            return ShopDataList.get(key);
        }
        return null;
    }
}
