package com.somrpg.swordofmagic7.Core.Generic.Parameter;

public interface GenericStatus {

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
