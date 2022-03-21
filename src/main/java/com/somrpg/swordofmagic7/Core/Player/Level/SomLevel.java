package com.somrpg.swordofmagic7.Core.Player.Level;

public class SomLevel {

    private int Level = 1;
    private int Exp = 0;

    public void setLevel(int level) {
        Level = level;
    }

    public void addLevel(int level) {
        Level += level;
    }

    public int getLevel() {
        return Level;
    }

    public void setExp(int exp) {
        Exp = exp;
    }

    public void addExp(int exp) {
        Exp += exp;
    }

    public int getExp() {
        return Exp;
    }
}
