package com.somrpg.swordofmagic7.Core.Player.Level;

import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;

public class LifeReqExp {
    private static final int[] reqExp = new int[GenericConfig.LifeMaxLevel];

    public static int getReqExp(int level) {
        int index = level-1;
        if (reqExp[index] == 0) {
            for (int i = 0; i < reqExp.length; i++) {
                double exp = 100;
                exp *= Math.pow(level, 1.8);
                exp *= Math.ceil(level/5d);
                reqExp[i] = (int) Math.ceil(exp);
            }
        }
        return reqExp[index];
    }
}
