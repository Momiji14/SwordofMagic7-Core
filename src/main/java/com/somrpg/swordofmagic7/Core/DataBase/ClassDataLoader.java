package com.somrpg.swordofmagic7.Core.DataBase;

import com.somrpg.swordofmagic7.Core.ClassesSkills.ClassData;
import com.somrpg.swordofmagic7.Core.ClassesSkills.SkillData;
import com.somrpg.swordofmagic7.Core.SomCore;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.somrpg.swordofmagic7.Core.Generic.GenericConfig.DataBasePath;

public interface ClassDataLoader extends DataBase {

    Map<Integer, ClassData> ClassGUI = new HashMap<>();
    Map<String, ClassData> ClassDataList = new HashMap<>();

    static void load() {
        for (File file : DataBase.dump(new File(DataBasePath, "ClassData/"))) {
            try {
                String fileName = file.getName().replace(".yml", "");
                FileConfiguration data = YamlConfiguration.loadConfiguration(file);
                if (!fileName.equals("GUI")) {
                    String color = data.getString("Color");
                    Material material = Material.getMaterial(data.getString("Icon", "BARRIER"));
                    String display = data.getString("Display");
                    List<String> lore = data.getStringList("Lore");
                    String nick = data.getString("Nick");
                    boolean productionClass = data.getBoolean("ProductionClass", false);
                    List<SkillData> skills = new ArrayList<>();
                    for (String str : data.getStringList("SkillList")) {
                        if (SkillDataLoader.getSkillData(str) != null) {
                            skills.add(SkillDataLoader.getSkillData(str));
                        }
                    }
                    ClassData classData = ClassData.create(fileName, material, display, color, nick, lore, productionClass);
                    classData.getSkillList().addAll(skills);
                    ClassDataList.put(fileName, classData);
                    SomCore.getSomTask().SyncTaskLater(() -> {
                        if (data.isSet("ReqClass")) for (String str : data.getStringList("ReqClass")) {
                            String[] split = str.split(":");
                            classData.getReqClass().put(getClassData(split[0]), Integer.parseInt(split[1]));
                        }
                    }, 1);
                } else {
                    SomCore.getSomTask().SyncTaskLater(() -> {
                        for (int i = 0; i < 54; i++) {
                            if (data.isSet("ClassGUI." + i)) {
                                ClassGUI.put(i, getClassData(data.getString("ClassGUI." + i)));
                            }
                        }
                    }, 1);
                }
            } catch (Exception e) {
                DataBase.loadError(file);
            }
        }
    }

    @Nullable
    static ClassData getClassData(String id) {
        if (ClassDataList.containsKey(id)) {
            return ClassDataList.get(id);
        }
        return null;
    }
}
