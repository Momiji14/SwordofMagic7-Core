package com.somrpg.swordofmagic7.Core.Player.Interface;

import com.somrpg.swordofmagic7.Core.ClassesSkills.ClassData;
import com.somrpg.swordofmagic7.Core.Player.Container.PlayerClassContainer;

public interface PlayerClass {

    PlayerClassContainer getPlayerClassContainer();

    default ClassData getTopClass() {
        return getPlayerClassContainer().getTopClass();
    }

    default void setClassSlot(int i, ClassData classData) {
        getPlayerClassContainer().setClassSlot(i, classData);
    }

    default void setClassLevel(ClassData classData, int level) {
        getPlayerClassContainer().setClassLevel(classData, level);
    }
    default void addClassLevel(ClassData classData, int level) {
        getPlayerClassContainer().addClassLevel(classData, level);
    }
    default int getClassLevel(ClassData classData) {
        return getPlayerClassContainer().getClassLevel(classData);
    }

    default void setClassExp(ClassData classData, int exp) {
        getPlayerClassContainer().setClassExp(classData, exp);
    }
    default void addClassExp(ClassData classData, int exp) {
        getPlayerClassContainer().addClassExp(classData, exp);
    }
    default int getClassExp(ClassData classData) {
        return getPlayerClassContainer().getClassExp(classData);
    }

    default String getClassExpPercent(ClassData classData) {
        return getPlayerClassContainer().getClassExpPercent(classData);
    }
}
