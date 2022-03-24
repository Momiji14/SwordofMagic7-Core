package com.somrpg.swordofmagic7.Core.DataBase;

import com.somrpg.swordofmagic7.Core.ClassesSkills.ClassData;
import com.somrpg.swordofmagic7.Core.ClassesSkills.SkillData;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Production.Base.SomRecipeContainer;
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

    Map<String, ClassData> ClassDataList = new HashMap<>();

    static void load() {
        for (File file : DataBase.dump(new File(DataBasePath, "ClassData/"))) {
            try {
                String fileName = file.getName().replace(".yml", "");
                FileConfiguration data = YamlConfiguration.loadConfiguration(file);
                String color = data.getString("Color");
                Material material = Material.getMaterial(data.getString("Icon", "BARRIER"));
                String display = data.getString("Display");
                List<String> lore = data.getStringList("Lore");
                String nick = data.getString("Nick");
                boolean productionClass = data.getBoolean("ProductionClass", false);
                List<SkillData> Skills = new ArrayList<>();
                for (String str : data.getStringList("SkillList")) {
                    if (SkillDataList.containsKey(str)) {
                        Skills.add(SkillDataList.get(str));
                    }
                }
                ClassData classData = new ClassData();
                classData.SkillList = Skills;
                ClassList.put(fileName, classData);
                ClassListDisplay.put(classData.Display, classData);
            } catch (Exception e) {
                loadError(file);
            }
        }
    }

    @Nullable
    default ClassData getClassData(String id) {
        if (ClassDataList.containsKey(id)) {
            return ClassDataList.get(id);
        }
        return null;
    }
}
