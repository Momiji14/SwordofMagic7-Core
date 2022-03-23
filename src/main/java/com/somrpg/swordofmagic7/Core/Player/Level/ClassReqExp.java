package com.somrpg.swordofmagic7.Core.Player.Level;

import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;

public interface ClassReqExp {
    int[] reqExp = new int[GenericConfig.ClassMaxLevel];

    static int getReqExp(int level) {
        if (level < 1) return Integer.MAX_VALUE;
        int index = level-1;
        if (reqExp[index] == 0) {
            for (int i = 0; i < reqExp.length; i++) {
                double exp = 100;
                exp *= Math.pow(level, 1.5);
                if (level >= 30) exp *= 3;
                reqExp[i] = (int) Math.ceil(exp);
            }
        }
        return reqExp[index];
    }
}
