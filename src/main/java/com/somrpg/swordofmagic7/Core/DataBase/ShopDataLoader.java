package com.somrpg.swordofmagic7.Core.DataBase;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Production.*;
import com.somrpg.swordofmagic7.Core.Production.Base.CraftSlot;
import com.somrpg.swordofmagic7.Core.Production.Base.SomCraftItem;
import com.somrpg.swordofmagic7.Core.Production.Base.SomRecipe;
import com.somrpg.swordofmagic7.Core.SomCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.somrpg.swordofmagic7.Core.Generic.GenericConfig.DataBasePath;

public interface ShopDataLoader extends DataBase {
    static void load() {
        for (File file : DataBase.dump(new File(DataBasePath, "ShopData/"))) {
            try {
                String fileName = file.getName().replace(".yml", "");
                FileConfiguration data = YamlConfiguration.loadConfiguration(file);
                Map<Integer, CraftSlot> craftSlots = new HashMap<>();
                int slot = 0;
                for (String str : data.getStringList("Data")) {
                    Map<String, String> mapData = new HashMap<>();
                    List<String> split = new ArrayList<>(List.of(str.split(",")));
                    SomItemStack item = SomItemDataLoader.getItem(split.get(0));
                    split.remove(0);
                    for (String str2 : split) {
                        String[] split2 = str2.split(":");
                        mapData.put(split2[0], split2[1]);
                    }
                    int amount = Integer.parseInt(mapData.getOrDefault("Amount", "1"));
                    int mel = 0;
                    SomRecipe recipe = null;
                    item.setAmount(amount);
                    if (mapData.containsKey("Slot")) slot = Integer.parseInt(mapData.get("Slot"));
                    if (mapData.containsKey("Mel")) mel = Integer.parseInt(mapData.get("Mel"));
                    if (mapData.containsKey("Recipe")) recipe = DataBase.SomRecipeList.get(mapData.get("Recipe"));
                    CraftSlot craftSlot = CraftSlot.create(ShopItem.create(recipe, item, mel));
                    craftSlots.put(slot, craftSlot);
                    slot++;
                }
                ShopData shopData = ShopData.create(fileName, craftSlots);
                ShopDataList.put(fileName, shopData);
            } catch (Exception e) {
                DataBase.loadError(file);
                e.printStackTrace();
            }
        }
    }
}
