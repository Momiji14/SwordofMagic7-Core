package com.somrpg.swordofmagic7.Core.Generic.Entity;

import com.somrpg.swordofmagic7.Core.Effect.EffectData;
import com.somrpg.swordofmagic7.Core.SomCore;

import java.util.Collection;

public interface BaseEntityInterface {
    void setHealth(double health);
    void addHealth(double health);
    double getHealth();
    void setMana(double mana);
    void addMana(double mana);
    double getMana();
    Collection<EffectData> getEffectSet();

    default void startEffectTimer() {
        SomCore.getSomTask().AsyncTaskTimer(() -> {
            for (EffectData effectData : getEffectSet()) {
                effectData.addTime(-1);
            }
            getEffectSet().removeIf(effectData -> effectData.getTime() < 1);
        }, 1);
    }
}
