package com.somrpg.swordofmagic7.Core.DataBase;

import com.somrpg.swordofmagic7.Core.SomCore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    public static List<File> dump(File file) {
        List<File> list = new ArrayList<>();
        File[] files = file.listFiles();
        for (File tmpFile : files) {
            if (!tmpFile.getName().equals(".sync")) {
                if (tmpFile.isDirectory()) {
                    list.addAll(dump(tmpFile));
                } else {
                    list.add(tmpFile);
                }
            }
        }
        return list;
    }

    public static void loadError(File file) {
        SomCore.log("§cLoading Error -> " + file.getName());
    }

    public static void startLoad() {
        SomItemDataLoader.load();
    }
}
