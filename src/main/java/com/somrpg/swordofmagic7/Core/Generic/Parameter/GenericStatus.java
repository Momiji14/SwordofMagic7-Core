package com.somrpg.swordofmagic7.Core.Generic.Parameter;

public interface GenericStatus {

    GenericStatusContainer getGenericStatusContainer();

    default void setStatusParameter(double maxHealth, double healthRegen, double maxMana, double manaRegen, double atk, double def, double acc, double eva, double criticalRate, double criticalResist) {
        getGenericStatusContainer().setStatusParameter(maxHealth, healthRegen, maxMana, manaRegen, atk, def, acc, eva, criticalRate, criticalResist);
    }

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

    default void setMaxHealth(double maxHealth) {
        getGenericStatusContainer().setMaxHealth(maxHealth);
    }
    default double getMaxHealth() {
        return getGenericStatusContainer().getMaxHealth();
    }
    default void setHealthRegen(double healthRegen) {
        getGenericStatusContainer().setHealthRegen(healthRegen);
    }
    default double getHealthRegen() {
        return getGenericStatusContainer().getHealthRegen();
    }
    default void setMaxMana(double maxMana) {
        getGenericStatusContainer().setMaxMana(maxMana);
    }
    default double getMaxMana() {
        return getGenericStatusContainer().getMaxMana();
    }
    default void setManaRegen(double manaRegen) {
        getGenericStatusContainer().setManaRegen(manaRegen);
    }
    default double getManaRegen() {
        return getGenericStatusContainer().getManaRegen();
    }
    default void setATK(double ATK) {
        getGenericStatusContainer().setATK(ATK);
    }
    default double getATK() {
        return getGenericStatusContainer().getATK();
    }
    default void setDEF(double DEF) {
        getGenericStatusContainer().setDEF(DEF);
    }
    default double getDEF() {
        return getGenericStatusContainer().getDEF();
    }
    default void setACC(double ACC) {
        getGenericStatusContainer().setACC(ACC);
    }
    default double getACC() {
        return getGenericStatusContainer().getACC();
    }
    default void setEVA(double EVA) {
        getGenericStatusContainer().setEVA(EVA);
    }
    default double getEVA() {
        return getGenericStatusContainer().getEVA();
    }
    default void setCriticalRate(double criticalRate) {
        getGenericStatusContainer().setCriticalRate(criticalRate);
    }
    default double getCriticalRate() {
        return getGenericStatusContainer().getCriticalRate();
    }
    default void setCriticalResist(double criticalResist) {
        getGenericStatusContainer().setCriticalResist(criticalResist);
    }
    default double getCriticalResist() {
        return getGenericStatusContainer().getCriticalResist();
    }
}
