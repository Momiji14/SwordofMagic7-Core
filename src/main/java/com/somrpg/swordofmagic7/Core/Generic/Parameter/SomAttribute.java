package com.somrpg.swordofmagic7.Core.Generic.Parameter;

public interface SomAttribute {

    SomAttributeContainer getAttributeContainer();

    default void setSTR(int STR) {
        getAttributeContainer().setSTR(STR);
    }
    default void addSTR(int STR) {
        getAttributeContainer().addSTR(STR);
    }
    default int getSTR() {
        return getAttributeContainer().getSTR();
    }

    default void setINT(int INT) {
        getAttributeContainer().setINT(INT);
    }
    default void addINT(int INT) {
        getAttributeContainer().addINT(INT);
    }
    default int getINT() {
        return getAttributeContainer().getINT();
    }

    default void setDEX(int DEX) {
        getAttributeContainer().setDEX(DEX);
    }
    default void addDEX(int DEX) {
        getAttributeContainer().addDEX(DEX);
    }
    default int getDEX() {
        return getAttributeContainer().getDEX();
    }

    default void setSPI(int SPI) {
        getAttributeContainer().setSPI(SPI);
    }
    default void addSPI(int SPI) {
        getAttributeContainer().addSPI(SPI);
    }
    default int getSPI() {
        return getAttributeContainer().getSPI();
    }

    default void setVIT(int VIT) {
        getAttributeContainer().setVIT(VIT);
    }
    default void addVIT(int VIT) {
        getAttributeContainer().addVIT(VIT);
    }
    default int getVIT() {
        return getAttributeContainer().getVIT();
    }
}
