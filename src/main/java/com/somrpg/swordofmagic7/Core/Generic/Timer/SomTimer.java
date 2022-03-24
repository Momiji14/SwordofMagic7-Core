package com.somrpg.swordofmagic7.Core.Generic.Timer;

public interface SomTimer {

    static SomTimer create() {
        return new SomTimerContainer();
    }

    void stop();

    void setTimer(String key, int tick);
    boolean isTimer(String key);
    void clearTimer(String key);
}
