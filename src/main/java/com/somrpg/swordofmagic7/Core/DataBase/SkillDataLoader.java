package com.somrpg.swordofmagic7.Core.DataBase;

import com.somrpg.swordofmagic7.Core.ClassesSkills.SkillData;
import com.somrpg.swordofmagic7.Core.ClassesSkills.SkillParameter;
import com.somrpg.swordofmagic7.Core.ClassesSkills.SkillParameterContainer;
import com.somrpg.swordofmagic7.Core.ClassesSkills.SkillType;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Item.EquipmentItemCategory;
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
                Material material = Material.getMaterial(data.getString("Icon", "END_CRYSTAL"));
                String display = data.getString("Display");
                List<String> lore = new ArrayList<>();
                for (String str : data.getStringList("Lore")) {
                    lore.add("§a§l" + str);
                }
                SkillType skillType = SkillType.valueOf(data.getString("SkillType"));
                int reqLevel = data.getInt("ReqLevel");
                int i = 0;
                List<SkillParameter> skillParameters = new ArrayList<>();
                while (data.isSet("Parameter-" + i + ".Display")) {
                    String display2 = data.getString("Parameter-" + i + ".Display");
                    double value = data.getDouble("Parameter-" + i + ".Value");
                    double increase = data.getDouble("Parameter-" + i + ".Increase");
                    String prefix = data.getString("Parameter-" + i + ".Prefix");
                    String suffix = data.getString("Parameter-" + i + ".Suffix");
                    int digit = data.getInt("Parameter-" + i + ".Format");
                    SkillParameter param = SkillParameter.create(display2, value, increase, prefix, suffix, digit);
                    skillParameters.add(param);
                    i++;
                }
                List<EquipmentItemCategory> reqMainHand = new ArrayList<>();
                for (String str : data.getStringList("ReqMainHand")) {
                    reqMainHand.add(EquipmentItemCategory.valueOf(str));
                }
                List<EquipmentItemCategory> reqOffHand = new ArrayList<>();
                for (String str : data.getStringList("ReqOffHand")) {
                    reqOffHand.add(EquipmentItemCategory.valueOf(str));
                }
                SkillData skillData = SkillData.create(fileName, material, display, lore, skillType, reqLevel, skillParameters, reqMainHand, reqOffHand);
                if (skillType.isActive()) {
                    skillData.setStack(data.getInt("Stack", 1));
                    skillData.setMana(data.getInt("Mana"));
                    skillData.setCastTime(data.getInt("CastTime"));
                    skillData.setRigidTime(data.getInt("RigidTime"));
                    skillData.setCoolTime(data.getInt("CoolTime"));
                }
                SkillDataList.put(skillData.getId(), skillData);
            } catch (Exception e) {
                DataBase.loadError(file);
            }
        }
    }

    static SkillData getSkillData(String id) {
        if (SkillDataList.containsKey(id)) {
            return SkillDataList.get(id);
        }
        return null;
    }

}
