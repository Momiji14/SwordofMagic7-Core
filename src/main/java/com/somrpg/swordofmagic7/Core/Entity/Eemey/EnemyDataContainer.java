package com.somrpg.swordofmagic7.Core.Entity.Eemey;

import com.somrpg.swordofmagic7.Core.Generic.Parameter.GenericStatus;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class EnemyDataContainer implements EnemyData{
    private final String id;
    private final String display;
    private final EntityType entityType;
    private final EnemyType enemyType;
    private final MobDisguise disguise;
    private final GenericStatus status;
    private final int exp;
    private final double mov;
    private final double reach;
    private final double search;
    private final boolean hostile;
    private final List<DropItemData> dropItemData = new ArrayList<>();
    private final List<DropRuneData> dropRuneData = new ArrayList<>();
    private final List<Double> HStop = new ArrayList<>();

    EnemyDataContainer(String id, String display, EntityType entityType, EnemyType enemyType, MobDisguise disguise, GenericStatus status, int exp, double mov, double reach, double search, boolean hostile) {
        this.id = id;
        this.display = display;
        this.entityType = entityType;
        this.enemyType = enemyType;
        this.disguise = disguise;
        this.status = status;
        this.exp = exp;
        this.mov = mov;
        this.reach = reach;
        this.search = search;
        this.hostile = hostile;
    }

    @Override
    public String getId() {
        return id;
    }
    @Override
    public String getDisplay() {
        return display;
    }
    @Override
    public EntityType getEntityType() {
        return entityType;
    }
    @Override
    public EnemyType getEnemyType() {
        return enemyType;
    }
    @Override
    public MobDisguise getDisguise() {
        return disguise;
    }
    @Override
    public GenericStatus getStatus() {
        return status;
    }
    @Override
    public int getExp() {
        return exp;
    }
    @Override
    public double getMov() {
        return mov;
    }
    @Override
    public double getReach() {
        return reach;
    }
    @Override
    public double getSearch() {
        return search;
    }
    @Override
    public boolean isHostile() {
        return hostile;
    }
    @Override
    public List<DropItemData> getDropItemData() {
        return dropItemData;
    }
    @Override
    public List<DropRuneData> getDropRuneData() {
        return dropRuneData;
    }
    @Override
    public List<Double> getHStop() {
        return HStop;
    }
}

