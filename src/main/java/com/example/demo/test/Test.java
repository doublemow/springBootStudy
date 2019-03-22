package com.example.demo.test;

import com.example.demo.task.MyThread;
import com.example.demo.util.MySession;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author cq
 */
@Slf4j
public class Test {
    public static void test(String id){
        if(MySession.futureList != null && MySession.futureList.size() > 0){
            MySession.futureList.get(0).cancel(false);
            MySession.futureList.remove(0);
        }
        ScheduledExecutorService scheduledExecutorService = MySession.getScheduledExecutorService();
        log.info("3秒后开始执行计划线程池服务...{}",new Date());
        try{
            final ScheduledFuture future = scheduledExecutorService.schedule(new MyThread(id), 3, TimeUnit.SECONDS);
            MySession.futureList.add(future);
        }catch (Exception e){
            log.error("{}",e);
        }
    }

    public static void main(String[] args) {
        test("123456");
    }
}
