package com.somrpg.swordofmagic7.Core.Generic.Parameter;

import com.somrpg.swordofmagic7.Core.Player.Level.SomLevel;
import org.bukkit.configuration.file.FileConfiguration;
import org.checkerframework.checker.nullness.qual.NonNull;

public class GenericStatusContainer extends SomLevel implements GenericStatus {
    private double MaxHealth = 0;
    private double HealthRegen = 0;
    private double MaxMana = 0;
    private double ManaRegen = 0;
    private double ATK = 0;
    private double DEF = 0;
    private double ACC = 0;
    private double EVA = 0;
    private double HLP = 0;
    private double CriticalRate = 0;
    private double CriticalResist = 0;

    public GenericStatusContainer() {}
    public GenericStatusContainer(@NonNull FileConfiguration data) {
        loadFile(data);
    }

    @Override
    public GenericStatusContainer getGenericStatusContainer() {
        return this;
    }

    @Override
    public GenericStatus setStatusParameter(double maxHealth, double healthRegen, double maxMana, double manaRegen, double atk, double def, double acc, double eva, double hlp, double criticalRate, double criticalResist) {
        MaxHealth = maxHealth;
        HealthRegen = healthRegen;
        MaxMana = maxMana;
        ManaRegen = manaRegen;
        ATK = atk;
        DEF = def;
        ACC = acc;
        EVA = eva;
        HLP = hlp;
        CriticalRate = criticalRate;
        CriticalResist = criticalResist;
        return this;
    }

    @Override
    public void loadFile(FileConfiguration data) {
        MaxHealth = data.getDouble("MaxHealth", 0);
        MaxMana = data.getDouble("MaxMana", 0);
        ATK = data.getDouble("ATK", 0);
        DEF = data.getDouble("DEF", 0);
        ACC = data.getDouble("ACC", 0);
        EVA = data.getDouble("EVA", 0);
        HLP = data.getDouble("HLP", 0);
        CriticalRate = data.getDouble("CriticalRate", 0);
        CriticalResist = data.getDouble("CriticalResist", 0);
    }

    @Override
    public GenericStatus addStatusParameter(GenericStatus param) {
        MaxHealth += param.getMaxHealth();
        HealthRegen += param.getHealthRegen();
        MaxMana += param.getMaxMana();
        ManaRegen += param.getManaRegen();
        ATK += param.getATK();
        DEF += param.getDEF();
        ACC += param.getACC();
        EVA += param.getEVA();
        HLP += param.getHLP();
        CriticalRate += param.getCriticalRate();
        CriticalResist += param.getCriticalResist();
        return this;
    }

    @Override
    public GenericStatus multiplyStatusParameter(GenericStatus param) {
        MaxHealth *= param.getMaxHealth();
        HealthRegen *= param.getHealthRegen();
        MaxMana *= param.getMaxMana();
        ManaRegen *= param.getManaRegen();
        ATK *= param.getATK();
        DEF *= param.getDEF();
        ACC *= param.getACC();
        EVA *= param.getEVA();
        HLP *= param.getHLP();
        CriticalRate *= param.getCriticalRate();
        CriticalResist *= param.getCriticalResist();
        return this;
    }

    @Override
    public void setStatusParameter(GenericStatus param) {
        MaxHealth = param.getMaxHealth();
        HealthRegen = param.getHealthRegen();
        MaxMana = param.getMaxMana();
        ManaRegen = param.getManaRegen();
        ATK = param.getATK();
        DEF = param.getDEF();
        ACC = param.getACC();
        EVA = param.getEVA();
        HLP = param.getHLP();
        CriticalRate = param.getCriticalRate();
        CriticalResist = param.getCriticalResist();
    }

    @Override
    public void setMaxHealth(double maxHealth) {
        MaxHealth = maxHealth;
    }

    @Override
    public double getMaxHealth() {
        return MaxHealth;
    }

    @Override
    public void setHealthRegen(double healthRegen) {
        HealthRegen = healthRegen;
    }

    @Override
    public double getHealthRegen() {
        return HealthRegen;
    }

    @Override
    public void setMaxMana(double maxMana) {
        MaxMana = maxMana;
    }

    @Override
    public double getMaxMana() {
        return MaxMana;
    }

    @Override
    public void setManaRegen(double manaRegen) {
        ManaRegen = manaRegen;
    }

    @Override
    public double getManaRegen() {
        return ManaRegen;
    }

    @Override
    public void setATK(double ATK) {
        this.ATK = ATK;
    }

    @Override
    public double getATK() {
        return ATK;
    }

    @Override
    public void setDEF(double DEF) {
        this.DEF = DEF;
    }

    @Override
    public double getDEF() {
        return DEF;
    }

    @Override
    public void setACC(double ACC) {
        this.ACC = ACC;
    }

    @Override
    public double getACC() {
        return ACC;
    }

    @Override
    public void setEVA(double EVA) {
        this.EVA = EVA;
    }

    @Override
    public double getEVA() {
        return EVA;
    }

    @Override
    public void setHLP(double HLP) {
        this.HLP = HLP;
    }

    @Override
    public double getHLP() {
        return HLP;
    }

    @Override
    public void setCriticalRate(double criticalRate) {
        CriticalRate = criticalRate;
    }

    @Override
    public double getCriticalRate() {
        return CriticalRate;
    }

    @Override
    public void setCriticalResist(double criticalResist) {
        CriticalResist = criticalResist;
    }

    @Override
    public double getCriticalResist() {
        return CriticalResist;
    }
}
