package com.somrpg.swordofmagic7.Core.Generic.Parameter;

import java.util.ArrayList;
import java.util.List;

import static com.somrpg.swordofmagic7.Core.Generic.DecoContent.decoLore;

public interface GenericStatus {

    GenericStatusContainer getGenericStatusContainer();

    default void setStatusParameter(double maxHealth, double healthRegen, double maxMana, double manaRegen, double atk, double def, double acc, double eva, double hlp, double criticalRate, double criticalResist) {
        setMaxHealth(maxHealth);
        setHealthRegen(healthRegen);
        setMaxMana(maxMana);
        setManaRegen(manaRegen);
        setATK(atk);
        setDEF(def);
        setHLP(hlp);
        setACC(acc);
        setEVA(eva);
        setCriticalRate(criticalRate);
        setCriticalResist(criticalResist);
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
    default void setHLP(double HLP) {
        getGenericStatusContainer().setHLP(HLP);
    }
    default double getHLP() {
        return getGenericStatusContainer().getHLP();
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

    default List<String> toStringList() {
        List<String> list = new ArrayList<>();
        if (getMaxHealth() > 0) list.add(decoLore("最大体力") + getMaxHealth());
        if (getHealthRegen() > 0) list.add(decoLore("体力自動回復") + getHealthRegen());
        if (getMaxMana() > 0) list.add(decoLore("最大マナ") + getMaxMana());
        if (getManaRegen() > 0) list.add(decoLore("マナ自動回復") + getManaRegen());
        if (getATK() > 0) list.add(decoLore("攻撃力") + getATK());
        if (getDEF() > 0) list.add(decoLore("防御力") + getDEF());
        if (getACC() > 0) list.add(decoLore("命中") + getACC());
        if (getEVA() > 0) list.add(decoLore("回避") + getEVA());
        if (getHLP() > 0) list.add(decoLore("治癒力") + getHLP());
        if (getCriticalRate() > 0) list.add(decoLore("クリティカル発生") + getCriticalRate());
        if (getCriticalResist() > 0) list.add(decoLore("クリティカル耐性") + getCriticalResist());
        return list;
    }
}
