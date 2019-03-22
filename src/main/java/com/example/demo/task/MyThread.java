package com.example.demo.task;

import com.example.demo.test.Test;
import com.example.demo.util.MySession;
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
    private String desktopId = null;
    public MyThread(String id) {
        desktopId = id;
        random = new Random();
    }
    @Override
    public void run() {
        try {
            log.info("desktopid,{}",desktopId);
            log.info("任务执行开始{}" , new Date());
            /**使用随机延时[0-3]秒来模拟执行任务*/
            int sleepNumber = random.nextInt(3);
            TimeUnit.SECONDS.sleep(sleepNumber);
            log.info("任务执行完毕{}" , new Date());
            --MySession.n;
            if(MySession.n <= 0){
                MySession.getScheduledExecutorService().shutdown();
                return;
            }
            Test.test(desktopId);
        } catch (Exception e) {
            log.error("{}",e);
        }
    }
}
