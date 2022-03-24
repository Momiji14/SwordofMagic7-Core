package com.somrpg.swordofmagic7.Core.Entity.Eemey;

import com.somrpg.swordofmagic7.Core.Generic.Parameter.GenericStatus;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.entity.EntityType;

import java.util.List;

public interface EnemyData {
    String getId();

    String getDisplay();

    EntityType getEntityType();

    EnemyType getEnemyType();

    MobDisguise getDisguise();

    GenericStatus getStatus();

    int getExp();

    double getMov();

    double getReach();

    double getSearch();

    boolean isHostile();

    List<DropItemData> getDropItemData();

    List<DropRuneData> getDropRuneData();

    List<Double> getHStop();
}
