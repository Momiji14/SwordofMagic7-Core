package com.somrpg.swordofmagic7.Core.DataBase;

import com.somrpg.swordofmagic7.Core.ClassesSkills.SkillData;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Production.Base.CraftSlot;
import com.somrpg.swordofmagic7.Core.Production.Base.SomRecipe;
import com.somrpg.swordofmagic7.Core.Production.ShopData;
import com.somrpg.swordofmagic7.Core.Production.ShopItem;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.somrpg.swordofmagic7.Core.Generic.GenericConfig.DataBasePath;

public interface SkillDataLoader {

    Map<String, SkillData> SkillDataList = new HashMap<>();

    static void load() {
        for (File file : DataBase.dump(new File(DataBasePath, "SkillData/"))) {
            try {
                String fileName = file.getName().replace(".yml", "");
                FileConfiguration data = YamlConfiguration.loadConfiguration(file);
                SkillData skillData = new SkillData();
                skillData.Id = fileName;
                skillData.Icon = Material.getMaterial(data.getString("Icon", "END_CRYSTAL"));
                skillData.Display = data.getString("Display");
                List<String> Lore = new ArrayList<>();
                for (String str : data.getStringList("Lore")) {
                    Lore.add("§a§l" + str);
                }
                skillData.Lore = Lore;
                skillData.SkillType = SkillType.valueOf(data.getString("SkillType"));
                skillData.ReqLevel = data.getInt("ReqLevel");
                int i = 0;
                while (data.isSet("Parameter-" + i + ".Display")) {
                    SkillParameter param = new SkillParameter();
                    param.Display = data.getString("Parameter-" + i + ".Display");
                    param.Value = data.getDouble("Parameter-" + i + ".Value");
                    param.Increase = data.getDouble("Parameter-" + i + ".Increase");
                    param.Prefix = data.getString("Parameter-" + i + ".Prefix");
                    param.Suffix = data.getString("Parameter-" + i + ".Suffix");
                    param.Format = data.getInt("Parameter-" + i + ".Format");
                    skillData.Parameter.add(param);
                    i++;
                }
                if (skillData.SkillType.isActive()) {
                    for (String str : data.getStringList("ReqMainHand")) {
                        skillData.ReqMainHand.add(EquipmentCategory.getEquipmentCategory(str));
                    }
                    for (String str : data.getStringList("ReqOffHand")) {
                        skillData.ReqOffHand.add(EquipmentCategory.getEquipmentCategory(str));
                    }
                    skillData.Stack = data.getInt("Stack", 1);
                    skillData.Mana = data.getInt("Mana");
                    skillData.CastTime = data.getInt("CastTime");
                    skillData.RigidTime = data.getInt("RigidTime");
                    skillData.CoolTime = data.getInt("CoolTime");
                }
                SkillDataList.put(skillData.Id, skillData);
                SkillDataDisplayList.put(skillData.Display, skillData);
            } catch (Exception e) {
                loadError(file);
            }
        }
    }

}
