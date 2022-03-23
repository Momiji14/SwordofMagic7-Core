package com.somrpg.swordofmagic7.Core.Generic.Parameter;

public interface GenericStatus {

    default void addStatusParameter(GenericStatus param) {
        setMaxHealth(getMaxHealth() + param.getMaxHealth());
        setHealthRegen(getManaRegen() + param.getHealthRegen());
        setMaxMana(getMaxMana() + param.getMaxMana());
        setManaRegen(getHealthRegen() + param.getManaRegen());
        setATK(getATK() + param.getATK());
        setDEF(getDEF() + param.getDEF());
        setACC(getACC() + param.getACC());
        setEVA(getEVA() + param.getEVA());
        setCriticalRate(getCriticalRate() + param.getCriticalRate());
        setCriticalResist(getCriticalResist() + param.getCriticalResist());
    }

    default void multiplyStatusParameter(GenericStatus param) {
        setMaxHealth(getMaxHealth() * param.getMaxHealth());
        setHealthRegen(getManaRegen() * param.getHealthRegen());
        setMaxMana(getMaxMana() * param.getMaxMana());
        setManaRegen(getHealthRegen() * param.getManaRegen());
        setATK(getATK() * param.getATK());
        setDEF(getDEF() * param.getDEF());
        setACC(getACC() * param.getACC());
        setEVA(getEVA() * param.getEVA());
        setCriticalRate(getCriticalRate() * param.getCriticalRate());
        setCriticalResist(getCriticalResist() * param.getCriticalResist());
    }

    default void setStatusParameter(GenericStatus param) {
        setMaxHealth(param.getMaxHealth());
        setHealthRegen(param.getHealthRegen());
        setMaxMana(param.getMaxMana());
        setManaRegen(param.getManaRegen());
        setATK(param.getATK());
        setDEF(param.getDEF());
        setACC(param.getACC());
        setEVA(param.getEVA());
        setCriticalRate(param.getCriticalRate());
        setCriticalResist(param.getCriticalResist());
    }

    void setMaxHealth(double maxHealth);
    double getMaxHealth();
    void setHealthRegen(double healthRegen);
    double getHealthRegen();
    void setMaxMana(double maxMana);
    double getMaxMana();
    void setManaRegen(double manaRegen);
    double getManaRegen();
    void setATK(double ATK);
    double getATK();
    void setDEF(double DEF);
    double getDEF();
    void setACC(double ACC);
    double getACC();
    void setEVA(double EVA);
    double getEVA();
    void setCriticalRate(double criticalRate);
    double getCriticalRate();
    void setCriticalResist(double criticalResist);
    double getCriticalResist();
}
