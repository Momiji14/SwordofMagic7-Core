package com.somrpg.swordofmagic7.Core.Entity;

import com.somrpg.swordofmagic7.Core.Generic.Effect.EffectData;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.GenericStatusContainer;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.SomAttributeContainer;

import java.util.Collection;
import java.util.HashSet;

public class BaseEntityContainer implements BaseEntity {
    private int level = 1;
    private int exp = 0;
    private double Health = 0;
    private double Mana = 0;
    private final Collection<EffectData> EffectSet = new HashSet<>();
    private final SomAttributeContainer attributeContainer = new SomAttributeContainer();
    private final GenericStatusContainer genericStatusContainer = new GenericStatusContainer();

    public BaseEntityContainer() {
        startEffectTimer();
    }

    @Override
    public BaseEntityContainer getBaseEntityContainer() {
        return this;
    }

    @Override
    public GenericStatusContainer getGenericStatusContainer() {
        return genericStatusContainer;
    }

    @Override
    public SomAttributeContainer getAttributeContainer() {
        return attributeContainer;
    }

    @Override public void setLevel(int level) {
        this.level = level;
    }
    @Override public int getLevel() {
        return level;
    }

    @Override public void setExp(int exp) {
        this.exp = exp;
    }
    @Override public int getExp() {
        return exp;
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
    public Collection<EffectData> getEffectSet() {
        return EffectSet;
    }
}
