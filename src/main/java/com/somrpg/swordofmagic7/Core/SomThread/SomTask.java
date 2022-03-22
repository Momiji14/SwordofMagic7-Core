package com.somrpg.swordofmagic7.Core.SomThread;

import com.somrpg.swordofmagic7.Core.SomCore;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.checkerframework.checker.nullness.qual.NonNull;

public class SomTask {
    private final BukkitScheduler scheduler = Bukkit.getScheduler();
    public BukkitTask AsyncTask(@NonNull SomRunnable runnable) {
        return scheduler.runTaskAsynchronously(SomCore.getPlugin(), runnable);
    }
    public BukkitTask AsyncTaskLater(@NonNull SomRunnable runnable, int tick) {
        return scheduler.runTaskLaterAsynchronously(SomCore.getPlugin(), runnable, tick);
    }
    public BukkitTask AsyncTaskTimer(@NonNull SomRunnable runnable, int tick) {
        return scheduler.runTaskTimerAsynchronously(SomCore.getPlugin(), runnable, 0, tick);
    }

    public BukkitTask SyncTask(@NonNull SomRunnable runnable) {
        return scheduler.runTask(SomCore.getPlugin(), runnable);
    }
    public BukkitTask SyncTaskLater(@NonNull SomRunnable runnable, int tick) {
        return scheduler.runTaskLater(SomCore.getPlugin(), runnable, tick);
    }
    public BukkitTask SyncTaskTimer(@NonNull SomRunnable runnable, int tick) {
        return scheduler.runTaskTimer(SomCore.getPlugin(), runnable, 0, tick);
    }

    public synchronized void sleepTick(int tick) {
        try {
            wait(tick * 50L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sleepMillis(int millis) {
        try {
            wait(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
