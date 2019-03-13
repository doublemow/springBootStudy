package com.example.demo.util;

import java.util.concurrent.ThreadFactory;

/**
 * @author cq
 */
public class MyThreadFactory implements ThreadFactory {
    private int counter;
    private String name;

    public MyThreadFactory(String name)
    {
        counter = 1;
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, name + "-Thread_" + counter);
        counter++;
        return t;
    }
}
