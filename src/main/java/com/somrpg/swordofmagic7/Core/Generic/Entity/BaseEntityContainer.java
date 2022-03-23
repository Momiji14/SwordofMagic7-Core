package com.somrpg.swordofmagic7.Core.Generic.Entity;

import com.somrpg.swordofmagic7.Core.Effect.EffectData;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.SomAttribute;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.SomAttributeContainer;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.GenericStatusContainer;

import java.util.Collection;
import java.util.HashSet;

public class BaseEntityContainer extends GenericStatusContainer implements BaseEntity, SomAttribute {
    private double Health = 0;
    private double Mana = 0;
    private final Collection<EffectData> EffectSet = new HashSet<>();
    private final SomAttributeContainer attribute = new SomAttributeContainer();

    public BaseEntityContainer() {
        startEffectTimer();
    }

    @Override
    public void setHealth(double health) {
        Health = Math.max(0, Math.min(getMaxHealth(), health));
    }

    @Override
    public void addHealth(double health) {
        setHealth(getHealth()+health);
    }

    @Override
    public double getHealth() {
        return Health;
    }

    @Override
    public void setHealthUnsafe(double health) {
        Health = health;
    }

    @Override
    public void setMana(double mana) {
        Mana = Math.max(0, Math.min(getMaxMana(), mana));
    }

    @Override
    public void addMana(double mana) {
        setMana(getMana()+mana);
    }

    @Override
    public double getMana() {
        return Mana;
    }

    @Override
    public void setManaUnsafe(double mana) {
        Mana = mana;
    }

    @Override
    public BaseEntityContainer getBaseEntityContainer() {
        return this;
    }

    @Override
    public Collection<EffectData> getEffectSet() {
        return EffectSet;
    }

    @Override
    public SomAttributeContainer getAttributeContainer() {
        return attribute;
    }
}
