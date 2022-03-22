package com.somrpg.swordofmagic7.Core.DataBase;

import com.somrpg.swordofmagic7.Core.Equipment.EquipmentItem;
import com.somrpg.swordofmagic7.Core.Equipment.EquipmentItemCategory;
import com.somrpg.swordofmagic7.Core.Equipment.SomEquipmentSlot;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.StatusParameter;
import com.somrpg.swordofmagic7.Core.Item.BaseItem;
import com.somrpg.swordofmagic7.Core.Item.ItemCategory;
import com.somrpg.swordofmagic7.Core.Item.RuneItem;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

import static com.somrpg.swordofmagic7.Core.Generic.GenericConfig.DataBasePath;

public class SomItemDataLoader implements SomLoader {
    public static void load() {
        for (File file : DataLoader.dump(new File(DataBasePath, "ItemData"))) {
            try {
                String fileName = file.getName().replace(".yml", "");
                FileConfiguration data = YamlConfiguration.loadConfiguration(file);
                Material material = Material.BARRIER;
                if (data.isSet("Material")) {
                    material = Material.getMaterial(data.getString("Material", "BARRIER"));
                }
                String display = data.getString("Display");
                List<String> lore = data.getStringList("Lore");
                int sell = data.getInt("Sell");
                ItemCategory category = ItemCategory.valueOf(data.getString("Category"));
                SomItemStack item = new SomItemStack(fileName, display, material, lore);
                BaseItem baseItem = new BaseItem(item, category, sell);
                switch (category) {
                    case Equipment -> {
                        EquipmentItemCategory equipmentItemCategory = EquipmentItemCategory.valueOf(data.getString("EquipmentCategory"));
                        SomEquipmentSlot equipmentSlot = SomEquipmentSlot.valueOf(data.getString("EquipmentSlot"));
                        item = new EquipmentItem(baseItem, equipmentItemCategory, equipmentSlot);
                    }
                }
                SomLoader.SomItemDataList.put(fileName, item);
            } catch (Exception e) {
                e.printStackTrace();
                DataLoader.loadError(file);
            }
        }

        for (File file : DataLoader.dump(new File(DataBasePath, "RuneData"))) {
            try {
                String fileName = file.getName().replace(".yml", "");
                FileConfiguration data = YamlConfiguration.loadConfiguration(file);
                Material material = Material.getMaterial(data.getString("Icon", "BARRIER"));
                String display = data.getString("Display");
                List<String> lore = data.getStringList("Lore");
                SomItemStack item = new SomItemStack(fileName, display, material, lore);
                RuneItem runeItem = new RuneItem(item, new StatusParameter(data));
                SomLoader.SomItemDataList.put(fileName, runeItem);
            } catch (Exception e) {
                e.printStackTrace();
                DataLoader.loadError(file);
            }
        }
    }

    public static SomItemStack getItem(String id) {
        if (SomItemDataList.containsKey(id)) {
            return SomItemDataList.get(id);
        }
        return new SomItemStack("Error Id -> " + id, Material.BARRIER);
    }
}
