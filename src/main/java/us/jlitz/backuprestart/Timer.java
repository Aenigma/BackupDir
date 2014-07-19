/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.jlitz.backuprestart;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Russell
 * @author Kevin Raoofi
 */
public class Timer {

    private final ScheduledExecutorService scheduler;

    public Timer() {
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    public void submit(Runnable r, long duration, TimeUnit durationUnit) {
        scheduler.scheduleAtFixedRate(
                r,
                duration, duration,
                durationUnit);
    }
}
