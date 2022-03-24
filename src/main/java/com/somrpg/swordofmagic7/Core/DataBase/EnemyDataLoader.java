package com.somrpg.swordofmagic7.Core.DataBase;

import com.somrpg.swordofmagic7.Core.Entity.Eemey.EnemyData;
import com.somrpg.swordofmagic7.Core.Entity.Eemey.EnemyDataContainer;
import com.somrpg.swordofmagic7.Core.Entity.Eemey.EnemyType;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.GenericStatus;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.GenericStatusContainer;
import com.somrpg.swordofmagic7.Core.Player.Production.Base.SomRecipeContainer;
import com.somrpg.swordofmagic7.Core.SomCore;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.SlimeWatcher;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.somrpg.swordofmagic7.Core.Generic.GenericConfig.DataBasePath;

public interface EnemyDataLoader {

    Map<String, EnemyData> EnemyDataList = new HashMap<>();

    static void load() {
        for (File file : DataBase.dump(new File(DataBasePath, "EnemyData/"))) {
            try {
                String fileName = file.getName().replace(".yml", "");
                FileConfiguration data = YamlConfiguration.loadConfiguration(file);

                String display = data.getString("Display");
                EntityType entityType = EntityType.fromName(data.getString("Type", "SKELETON").toUpperCase());
                EnemyType enemyType = EnemyType.valueOf(data.getString("EnemyType", "Normal"));
                if (entityType == null) {
                    entityType = EntityType.SKELETON;
                    SomCore.log("Illegal EntityType -> " + file.getName());
                }
                MobDisguise disguise = null;
                if (data.isSet("Disguise.Type")) {
                    disguise = new MobDisguise(DisguiseType.valueOf(data.getString("Disguise.Type").toUpperCase()));
                    if (disguise.getType() == DisguiseType.SLIME) {
                        SlimeWatcher slimeWatcher = new SlimeWatcher(disguise);
                        slimeWatcher.setSize(data.getInt("Disguise.Size"));
                        disguise.setWatcher(slimeWatcher);
                    }
                }
                int exp = data.getInt("Exp", 10);
                double mov = data.getDouble("Mov", 1.2);
                double reach = data.getDouble("Reach", 1.5);
                double search = data.getDouble("Search", 32);
                boolean hostile = data.getBoolean("Hostile", false);
                GenericStatus status = new GenericStatusContainer(data);
                EnemyData enemyData = new EnemyDataContainer(fileName, display, entityType, enemyType, disguise, status, exp, mov, reach, search, hostile);
                EnemyDataList.put(fileName, enemyData);
            } catch (Exception e) {
                DataBase.loadError(file);
            }
        }
    }

    static EnemyData getEnemyData(String key) {
        if (EnemyDataList.containsKey(key)) {
            return EnemyDataList.get(key);
        }
        return null;
    }

}
