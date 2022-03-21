package com.somrpg.swordofmagic7.Core.SomThread;

import com.somrpg.swordofmagic7.Core.SomCore;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public class SomTask {
    private final BukkitScheduler scheduler = Bukkit.getScheduler();
    public BukkitTask AsyncTask(SomRunnable runnable) {
        return scheduler.runTaskAsynchronously(SomCore.getPlugin(), runnable);
    }
    public BukkitTask AsyncTaskLater(SomRunnable runnable, int tick) {
        return scheduler.runTaskLaterAsynchronously(SomCore.getPlugin(), runnable, tick);
    }
    public BukkitTask AsyncTaskTimer(SomRunnable runnable, int tick) {
        return scheduler.runTaskTimerAsynchronously(SomCore.getPlugin(), runnable, 0, tick);
    }

    public synchronized void sleepTick(int tick) {
        try {
            wait(tick * 50L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
