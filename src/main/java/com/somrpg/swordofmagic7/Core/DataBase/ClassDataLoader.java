package com.somrpg.swordofmagic7.Core.DataBase;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Production.Base.SomRecipeContainer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.somrpg.swordofmagic7.Core.Generic.GenericConfig.DataBasePath;

public interface ClassDataLoader extends DataBase {

    static void load() {
        for (File file : DataBase.dump(new File(DataBasePath, "Recipe/"))) {
            try {
                String fileName = file.getName().replace(".yml", "");
                FileConfiguration data = YamlConfiguration.loadConfiguration(file);

                List<SomItemStack> reqStack = new ArrayList<>();
                for (String str : data.getStringList("ReqStack")) {
                    String[] split = str.split(",");
                    SomItemStack itemStack = SomItemDataLoader.getItem(split[0]);
                    itemStack.setAmount(Integer.parseInt(split[1].replace("Amount:", "")));
                    reqStack.add(itemStack);
                }
                SomRecipeContainer itemRecipe = new SomRecipeContainer(reqStack);
                SomRecipeList.put(fileName, itemRecipe);
            } catch (Exception e) {
                DataBase.loadError(file);
            }
        }
    }
}
