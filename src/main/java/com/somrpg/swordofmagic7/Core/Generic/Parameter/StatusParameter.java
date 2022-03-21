package com.somrpg.swordofmagic7.Core.Generic.Parameter;

import com.somrpg.swordofmagic7.Core.Player.Level.SomLevel;
import org.bukkit.configuration.file.FileConfiguration;

public class StatusParameter extends SomLevel implements StatusParameterInterface {
    private double MaxHealth = 0;
    private double HealthRegen = 0;
    private double MaxMana = 0;
    private double ManaRegen = 0;
    private double ATK = 0;
    private double DEF = 0;
    private double ACC = 0;
    private double EVA = 0;
    private double CriticalRate = 0;
    private double CriticalResist = 0;

    public StatusParameter() {}
    public StatusParameter(FileConfiguration data) {
        load(data);
    }

    public void load(FileConfiguration data) {
        MaxHealth = data.getDouble("MaxHealth", 0d);
        MaxMana = data.getDouble("MaxMana", 0d);
        ATK = data.getDouble("ATK", 0d);
        DEF = data.getDouble("DEF", 0d);
        ACC = data.getDouble("ACC", 0d);
        EVA = data.getDouble("EVA", 0d);
        CriticalRate = data.getDouble("CriticalRate", 0d);
        CriticalResist = data.getDouble("CriticalResist", 0d);
    }

    public void setMaxHealth(double maxHealth) {
        MaxHealth = maxHealth;
    }

    public double getMaxHealth() {
        return MaxHealth;
    }

    public void setHealthRegen(double healthRegen) {
        HealthRegen = healthRegen;
    }

    public double getHealthRegen() {
        return HealthRegen;
    }

    public void setMaxMana(double maxMana) {
        MaxMana = maxMana;
    }

    public double getMaxMana() {
        return MaxMana;
    }

    public void setManaRegen(double manaRegen) {
        ManaRegen = manaRegen;
    }

    public double getManaRegen() {
        return ManaRegen;
    }

    public void setATK(double ATK) {
        this.ATK = ATK;
    }

    public double getATK() {
        return ATK;
    }

    public void setDEF(double DEF) {
        this.DEF = DEF;
    }

    public double getDEF() {
        return DEF;
    }

    public void setACC(double ACC) {
        this.ACC = ACC;
    }

    public double getACC() {
        return ACC;
    }

    public void setEVA(double EVA) {
        this.EVA = EVA;
    }

    public double getEVA() {
        return EVA;
    }

    public void setCriticalRate(double criticalRate) {
        CriticalRate = criticalRate;
    }

    public double getCriticalRate() {
        return CriticalRate;
    }

    public void setCriticalResist(double criticalResist) {
        CriticalResist = criticalResist;
    }

    public double getCriticalResist() {
        return CriticalResist;
    }
}
