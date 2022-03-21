package com.somrpg.swordofmagic7.Core.Generic.Entity;

import com.somrpg.swordofmagic7.Core.Effect.EffectData;
import com.somrpg.swordofmagic7.Core.Generic.Parameter.StatusParameter;

import java.util.Collection;
import java.util.HashSet;

public class BaseEntity extends StatusParameter implements BaseEntityInterface {
    private double Health = 0;
    private double Mana = 0;
    private final Collection<EffectData> EffectSet = new HashSet<>();

    public BaseEntity() {
        startEffectTimer();
    }

    @Override
    public Collection<EffectData> getEffectSet() {
        return EffectSet;
    }

    @Override
    public void setHealth(double health) {
        Health = Math.max(0, Math.min(health, getMaxHealth()));
    }

    public void setHealthUnsafe(double health) {
        Health = health;
    }

    @Override
    public void addHealth(double health) {
        setHealth(Health + health);
    }

    @Override
    public double getHealth() {
        return Health;
    }

    @Override
    public void setMana(double mana) {
        Mana = Math.max(0, Math.min(mana, getMaxMana()));
    }

    public void setManaUnsafe(double mana) {
        Mana = mana;
    }

    @Override
    public void addMana(double mana) {
        setMana(Mana + mana);
    }

    @Override
    public double getMana() {
        return Mana;
    }
}
