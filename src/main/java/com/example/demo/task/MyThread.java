package com.example.demo.task;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
/**
 * @author cq
 */
public class MyThread implements Runnable{
    private Random random = null;
    public MyThread() {
        random = new Random();
    }
    @Override
    public void run() {
        try {
            log.info("任务执行开始{}" , new Date());
            /**使用随机延时[0-3]秒来模拟执行任务*/
            int sleepNumber = random.nextInt(3);
            TimeUnit.SECONDS.sleep(sleepNumber);
            log.info("任务执行完毕{}" , new Date());
        } catch (Exception e) {
            log.error("{}",e);
        }
    }
}
