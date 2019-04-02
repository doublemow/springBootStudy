package com.example.demo.util;

import com.example.demo.example.algorithms.Heap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class MySession {
    public static Heap heap;
    public static int n = 25;
    private volatile static ScheduledExecutorService scheduledExecutorService;
    public volatile static List<ScheduledFuture> futureList = new ArrayList<>();

    public synchronized static ScheduledExecutorService getScheduledExecutorService() {
        if(scheduledExecutorService == null || scheduledExecutorService.isShutdown()){
            scheduledExecutorService = new ScheduledThreadPoolExecutor(5,new MyThreadFactory("Deploy"));
        }
        return scheduledExecutorService;
    }

}
