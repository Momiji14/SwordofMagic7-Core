package com.somrpg.swordofmagic7.Core.DataBase;

import com.somrpg.swordofmagic7.Core.Generic.Item.*;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.GenericStatusContainer;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.somrpg.swordofmagic7.Core.Generic.GenericConfig.DataBasePath;

public interface SomItemDataLoader extends DataBase {
    Map<String, SomItemStack> SomItemDataList = new HashMap<>();

    static void load() {
        for (File file : DataBase.dump(new File(DataBasePath, "ItemData"))) {
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
                        EquipmentItem equipmentItem = (EquipmentItem) item;
                        equipmentItem.getStatusParameter().loadFile(data);
                    }
                    case Potion -> {
                        SomPotionItem potionItem = new SomPotionItem(item, ItemCategory.Potion, sell);
                        potionItem.setHealth(data.getDouble("Health", 0));
                        potionItem.setMana(data.getDouble("Mana", 0));
                        item = potionItem;
                    }
                    case Cook -> {
                        SomCookItem cookItem = new SomCookItem(item, ItemCategory.Potion, sell);
                        cookItem.setHealth(data.getDouble("Health", 0));
                        cookItem.setMana(data.getDouble("Mana", 0));
                        item = cookItem;
                    }
                    default -> item = baseItem;
                }
                if (material == Material.PLAYER_HEAD) {
                    item.setMaterialData(data.getString("PlayerHead"));
                }
                SomItemDataList.put(fileName, item);
            } catch (Exception e) {
                e.printStackTrace();
                DataBase.loadError(file);
            }
        }

        for (File file : DataBase.dump(new File(DataBasePath, "RuneData"))) {
            try {
                String fileName = file.getName().replace(".yml", "");
                FileConfiguration data = YamlConfiguration.loadConfiguration(file);
                Material material = Material.getMaterial(data.getString("Icon", "BARRIER"));
                String display = data.getString("Display");
                List<String> lore = data.getStringList("Lore");
                SomItemStack item = new SomItemStack(fileName, display, material, lore);
                RuneItem runeItem = new RuneItem(item, new GenericStatusContainer(data));
                SomItemDataList.put(fileName, runeItem);
            } catch (Exception e) {
                e.printStackTrace();
                DataBase.loadError(file);
            }
        }
    }

    @Nullable
    static SomItemStack getSomItemStack(String id) {
        if (SomItemDataList.containsKey(id)) {
            return SomItemDataList.get(id).cloneSomItemStack();
        }
        return null;
    }
    @Nullable
    static BaseItem getItem(String id) {
        if (SomItemDataList.containsKey(id)) {
            return ((BaseItem) SomItemDataList.get(id)).cloneItem();
        }
        return null;
    }

    @Nullable
    static EquipmentItem getEquipment(String id) {
        if (SomItemDataList.containsKey(id)) {
            return ((EquipmentItem) SomItemDataList.get(id)).cloneEquipment();
        }
        return null;
    }

    @Nullable
    static RuneItem getRune(String id) {
        if (SomItemDataList.containsKey(id)) {
            return ((RuneItem) SomItemDataList.get(id)).cloneRune();
        }
        return null;
    }

}
