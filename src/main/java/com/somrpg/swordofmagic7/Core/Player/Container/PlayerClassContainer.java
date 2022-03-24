package com.somrpg.swordofmagic7.Core.Player.Container;

import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Player.ClassesSkills.ClassData;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerClass;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.Level.ClassReqExp;

import java.util.HashMap;
import java.util.Map;

import static com.somrpg.swordofmagic7.Core.Generic.DecoFormat.ScaleDigit;

public class PlayerClassContainer implements PlayerClass {

    private final ClassData[] classSlot = new ClassData[GenericConfig.ClassSlot];
    private final Map<ClassData, Integer> level = new HashMap<>();
    private final HashMap<ClassData, Integer> exp = new HashMap<>();

    private final PlayerData playerData;

    PlayerClassContainer(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public PlayerClassContainer getPlayerClassContainer() {
        return this;
    }

    @Override
    public ClassData getTopClass() {
        for (int i = classSlot.length-1; i > 0; i--) {
            if (classSlot[i] != null) return classSlot[i];
        }
        return null;
    }

    @Override
    public ClassData[] getClassSlot() {
        return classSlot;
    }

    @Override
    public void setClassSlot(int i, ClassData classData) {
        classSlot[i] = classData;
    }

    @Override
    public void setClassLevel(ClassData classData, int level) {
        this.level.put(classData, level);
    }

    @Override
    public void addClassLevel(ClassData classData, int level) {
        this.level.merge(classData, level, Integer::sum);
    }

    @Override
    public int getClassLevel(ClassData classData) {
        return level.getOrDefault(classData, 1);
    }

    @Override
    public void setClassExp(ClassData classData, int exp) {
        this.exp.put(classData, exp);
    }

    @Override
    public void addClassExp(ClassData classData, int exp) {
        this.exp.merge(classData, exp, Integer::sum);
    }

    @Override
    public int getClassExp(ClassData classData) {
        return exp.getOrDefault(classData, 0);
    }

    @Override
    public String getClassExpPercent(ClassData classData) {
        return ScaleDigit((double) getClassExp(classData)/ ClassReqExp.getReqExp(getClassLevel(classData))*100f, playerData.getViewDigit()) + "%";
    }
}
