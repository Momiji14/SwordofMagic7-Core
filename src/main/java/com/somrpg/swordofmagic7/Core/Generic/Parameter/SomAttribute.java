package com.somrpg.swordofmagic7.Core.Generic.Parameter;

public class SomAttribute implements AttributeInterface{
    private int STR = 0;
    private int INT = 0;
    private int DEX = 0;
    private int SPI = 0;
    private int VIT = 0;

    @Override
    public void setSTR(int STR) {
        this.STR = STR;
    }

    @Override
    public void addSTR(int STR) {
        this.STR += STR;
    }

    @Override
    public int getSTR() {
        return STR;
    }

    @Override
    public void setINT(int INT) {
        this.INT = INT;
    }

    @Override
    public void addINT(int INT) {
        this.INT += INT;
    }

    @Override
    public int getINT() {
        return INT;
    }

    @Override
    public void setDEX(int DEX) {
        this.DEX = DEX;
    }

    @Override
    public void addDEX(int DEX) {
        this.DEX += DEX;
    }

    @Override
    public int getDEX() {
        return DEX;
    }

    @Override
    public void setSPI(int SPI) {
        this.SPI = SPI;
    }

    @Override
    public void addSPI(int SPI) {
        this.SPI += SPI;
    }

    @Override
    public int getSPI() {
        return SPI;
    }

    @Override
    public void setVIT(int VIT) {
        this.VIT = VIT;
    }

    @Override
    public void addVIT(int VIT) {
        this.VIT += VIT;
    }

    @Override
    public int getVIT() {
        return VIT;
    }
}
