package com.somrpg.swordofmagic7.Core.DataBase;

import com.somrpg.swordofmagic7.Core.Entity.Eemey.SomSpawner;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.SomCore;
import org.bukkit.Material;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface DataBase {

    SomItemStack ErrorItemStack = new SomItemStack("Error SomItemStack", Material.BARRIER);
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
        SkillDataLoader.load();
        ClassDataLoader.load();
        EnemyDataLoader.load();
        SomSpawnerLoader.load();
    }

    static void singleLoad() {
        WarpGateLoader.load();
        TeleportGateLoader.load();

        for (SomSpawner spawner : SomSpawnerLoader.SpawnerDataList.values()) {
            spawner.start();
        }
    }
}
