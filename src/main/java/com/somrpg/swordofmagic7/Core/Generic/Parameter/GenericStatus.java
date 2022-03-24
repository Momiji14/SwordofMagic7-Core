package com.somrpg.swordofmagic7.Core.Generic.Parameter;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

import static com.somrpg.swordofmagic7.Core.Generic.DecoContent.decoLore;
import static com.somrpg.swordofmagic7.Core.Generic.DecoFormat.ScaleDigit;

public interface GenericStatus extends Cloneable {

    GenericStatusContainer getGenericStatusContainer();

    default GenericStatus setStatusParameter(double maxHealth, double healthRegen, double maxMana, double manaRegen, double atk, double def, double acc, double eva, double hlp, double criticalRate, double criticalResist) {
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
        return getGenericStatusContainer();
    }

    default GenericStatus setAllStatusParameter(double value) {
        setMaxHealth(value);
        setHealthRegen(value);
        setMaxMana(value);
        setManaRegen(value);
        setATK(value);
        setDEF(value);
        setACC(value);
        setEVA(value);
        setCriticalRate(value);
        setCriticalResist(value);
        return getGenericStatusContainer();
    }

    default GenericStatus addStatusParameter(GenericStatus param) {
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
        return getGenericStatusContainer();
    }

    default GenericStatus multiplyStatusParameter(GenericStatus param) {
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
        return getGenericStatusContainer();
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

    default void loadFile(FileConfiguration data) {
        getGenericStatusContainer().loadFile(data);
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

    default List<String> toStringList(int digit) {
        List<String> list = new ArrayList<>();
        if (getMaxHealth() > 0) list.add(decoLore("最大体力") + ScaleDigit(getMaxHealth(), digit));
        if (getHealthRegen() > 0) list.add(decoLore("体力自動回復") + ScaleDigit(getHealthRegen(), digit));
        if (getMaxMana() > 0) list.add(decoLore("最大マナ") + ScaleDigit(getMaxMana(), digit));
        if (getManaRegen() > 0) list.add(decoLore("マナ自動回復") + ScaleDigit(getManaRegen(), digit));
        if (getATK() > 0) list.add(decoLore("攻撃力") + ScaleDigit(getATK(), digit));
        if (getDEF() > 0) list.add(decoLore("防御力") + ScaleDigit(getDEF(), digit));
        if (getACC() > 0) list.add(decoLore("命中") + ScaleDigit(getACC(), digit));
        if (getEVA() > 0) list.add(decoLore("回避") + ScaleDigit(getEVA(), digit));
        if (getHLP() > 0) list.add(decoLore("治癒力") + ScaleDigit(getHLP(), digit));
        if (getCriticalRate() > 0) list.add(decoLore("クリティカル発生") + ScaleDigit(getCriticalRate(), digit));
        if (getCriticalResist() > 0) list.add(decoLore("クリティカル耐性") + ScaleDigit(getCriticalResist(), digit));
        return list;
    }
}
