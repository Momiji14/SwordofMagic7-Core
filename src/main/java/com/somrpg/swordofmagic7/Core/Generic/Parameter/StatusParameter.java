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
    public void setStatusParameter(double maxHealth, double healthRegen, double maxMana, double manaRegen, double atk, double def, double acc, double eva, double criticalRate, double criticalResist) {
        MaxHealth = maxHealth;
        HealthRegen = healthRegen;
        MaxMana = maxMana;
        ManaRegen = manaRegen;
        ATK = atk;
        DEF = def;
        ACC = acc;
        EVA = eva;
        CriticalRate = criticalRate;
        CriticalResist = criticalResist;
    }

    public void load(FileConfiguration data) {
        MaxHealth = data.getDouble("MaxHealth", 0);
        MaxMana = data.getDouble("MaxMana", 0);
        ATK = data.getDouble("ATK", 0);
        DEF = data.getDouble("DEF", 0);
        ACC = data.getDouble("ACC", 0);
        EVA = data.getDouble("EVA", 0);
        CriticalRate = data.getDouble("CriticalRate", 0);
        CriticalResist = data.getDouble("CriticalResist", 0);
    }

    public void addStatusParameter(StatusParameter param) {
        MaxHealth += param.getMaxHealth();
        HealthRegen += param.getHealthRegen();
        MaxMana += param.getMaxMana();
        ManaRegen += param.getManaRegen();
        ATK += param.getATK();
        DEF += param.getDEF();
        ACC += param.getACC();
        EVA += param.getEVA();
        CriticalRate += param.getCriticalRate();
        CriticalResist += param.getCriticalResist();
    }

    public void multiplyStatusParameter(StatusParameter param) {
        MaxHealth *= param.getMaxHealth();
        HealthRegen *= param.getHealthRegen();
        MaxMana *= param.getMaxMana();
        ManaRegen *= param.getManaRegen();
        ATK *= param.getATK();
        DEF *= param.getDEF();
        ACC *= param.getACC();
        EVA *= param.getEVA();
        CriticalRate *= param.getCriticalRate();
        CriticalResist *= param.getCriticalResist();
    }

    public void setStatusParameter(StatusParameter param) {
        MaxHealth = param.getMaxHealth();
        HealthRegen = param.getHealthRegen();
        MaxMana = param.getMaxMana();
        ManaRegen = param.getManaRegen();
        ATK = param.getATK();
        DEF = param.getDEF();
        ACC = param.getACC();
        EVA = param.getEVA();
        CriticalRate = param.getCriticalRate();
        CriticalResist = param.getCriticalResist();
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
