package com.somrpg.swordofmagic7.Core.Effect;

public class EffectData {
    private int Time;
    private final SomEffectType effectType;

    public EffectData(SomEffectType effectType, int time) {
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

    public SomEffectType getEffectType() {
        return effectType;
    }
}
