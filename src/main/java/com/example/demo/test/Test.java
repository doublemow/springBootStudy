package com.example.demo.test;

import com.example.demo.task.MyThread;
import com.example.demo.util.MyThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author cq
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        MyThreadFactory threadFactory = new MyThreadFactory("deploy_desktop");
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(10,threadFactory);
        log.info("3秒后开始执行计划线程池服务...{}",new Date());
        scheduledExecutorService.schedule(new MyThread(), 3, TimeUnit.SECONDS);
    }
}
