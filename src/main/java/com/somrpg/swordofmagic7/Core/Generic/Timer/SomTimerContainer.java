package com.somrpg.swordofmagic7.Core.Generic.Timer;

import com.somrpg.swordofmagic7.Core.SomCore;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SomTimerContainer implements SomTimer {

    private static final int period = 5;

    private final BukkitTask timerTask;
    private final Map<String, Integer> TimerTable = new HashMap<>();

    SomTimerContainer() {
        timerTask = SomCore.getSomTask().AsyncTaskLater(() -> {
            Set<String> removeIf = new HashSet<>();
            for (Map.Entry<String, Integer> entry : TimerTable.entrySet()) {
                TimerTable.merge(entry.getKey(), -period, Integer::sum);
                if (entry.getValue() < 0) removeIf.add(entry.getKey());
            }
            for (String remove : removeIf) {
                TimerTable.remove(remove);
            }
        }, period);
    }

    @Override
    public void stop() {
        timerTask.cancel();
    }

    @Override
    public void setTimer(String key, int tick) {
        TimerTable.put(key, tick);
    }

    @Override
    public int getTimer(String key) {
        return TimerTable.getOrDefault(key, -1);
    }

    @Override
    public boolean isTimer(String key) {
        return TimerTable.containsKey(key);
    }

    @Override
    public void clearTimer(String key) {
        TimerTable.remove(key);
    }


}
