package com.somrpg.swordofmagic7.Core.Effect;

public class EffectData {
    private int Time;
    private final EffectType effectType;

    public EffectData(EffectType effectType, int time) {
        this.effectType = effectType;
        Time = time;
    }

    public void setTime(int time) {
        Time = time;
    }

    public void addTime(int time) {
        Time += time;
    }

    public int getTime() {
        return Time;
    }

    public EffectType getEffectType() {
        return effectType;
    }
}
