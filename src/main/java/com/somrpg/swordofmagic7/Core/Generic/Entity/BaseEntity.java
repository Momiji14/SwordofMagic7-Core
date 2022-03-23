package com.somrpg.swordofmagic7.Core.Generic.Entity;

import com.somrpg.swordofmagic7.Core.Effect.EffectData;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.GenericStatus;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.SomAttribute;
import com.somrpg.swordofmagic7.Core.SomCore;

import java.util.Collection;

public interface BaseEntity extends GenericStatus, SomAttribute {

    BaseEntityContainer getBaseEntityContainer();

    default void setHealth(double health) {
        getBaseEntityContainer().setHealth(health);
    }
    default void addHealth(double health) {
        getBaseEntityContainer().addHealth(health);
    }
    default double getHealth() {
        return getBaseEntityContainer().getHealth();
    }
    default void setMana(double mana) {
        getBaseEntityContainer().setMana(mana);
    }
    default void addMana(double mana) {
        getBaseEntityContainer().addMana(mana);
    }
    default double getMana() {
        return getBaseEntityContainer().getMana();
    }

    default void setHealthUnsafe(double health) {
        getBaseEntityContainer().setHealth(health);
    }
    default void setManaUnsafe(double mana) {
        getBaseEntityContainer().setMana(mana);
    }

    default Collection<EffectData> getEffectSet() {
        return getBaseEntityContainer().getEffectSet();
    }

    default void startEffectTimer() {
        SomCore.getSomTask().AsyncTaskTimer(() -> {
            for (EffectData effectData : getEffectSet()) {
                effectData.addTime(-1);
            }
            getEffectSet().removeIf(effectData -> effectData.getTime() < 1);
        }, 1);
    }
}
