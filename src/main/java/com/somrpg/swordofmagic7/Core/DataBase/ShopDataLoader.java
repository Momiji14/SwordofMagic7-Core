package com.somrpg.swordofmagic7.Core.DataBase;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Production.*;
import com.somrpg.swordofmagic7.Core.Production.Base.SomCraftItem;
import com.somrpg.swordofmagic7.Core.Production.Base.SomRecipe;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.somrpg.swordofmagic7.Core.Generic.GenericConfig.DataBasePath;

public interface ShopDataLoader extends DataBase {
    static void load() {
        for (File file : DataBase.dump(new File(DataBasePath, "ShopData/"))) {
            try {
                String fileName = file.getName().replace(".yml", "");
                FileConfiguration data = YamlConfiguration.loadConfiguration(file);
                Map<Integer, SomCraftItem> craftItems = new HashMap<>();
                int slot = 0;
                for (String str : data.getStringList("Data")) {
                    Map<String, String> mapData = new HashMap<>();
                    String[] split = str.split(",");
                    for (String str2 : split) {
                        String[] split2 = str2.split(":");
                        mapData.put(split2[0], split2[1]);
                    }
                    SomItemStack item = SomItemDataLoader.getItem(split[0]);
                    int amount = Integer.parseInt(mapData.getOrDefault("Amount", "1"));
                    int mel = 0;
                    SomRecipe recipe = null;
                    item.setAmount(amount);
                    if (mapData.containsKey("Slot")) slot = Integer.parseInt(mapData.get("Slot"));
                    if (mapData.containsKey("Mel")) mel = Integer.parseInt(mapData.get("Mel"));
                    if (mapData.containsKey("Recipe")) recipe = DataBase.SomRecipeList.get(mapData.get("Recipe"));
                    ShopItem shopItem = ShopItem.create(recipe, SomItemDataLoader.getItem(split[0]), mel);
                    craftItems.put(slot, shopItem);
                    slot++;
                }
                ShopData shopData = ShopData.create(fileName, craftItems);
                ShopDataList.put(fileName, shopData);
            } catch (Exception e) {
                DataBase.loadError(file);
            }
        }
    }
}
