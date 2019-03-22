package com.example.demo.task;

import com.example.demo.model.User;
import com.example.demo.test.Test;
import com.example.demo.util.MySession;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * @author cq
 */
@Slf4j
public class MyThread implements Runnable{
    private Random random = null;
    private Long userId = null;
    private Method method;
    private Object clazz;

    public MyThread(Long id, Method method,Object clazz) {
        this.method = method;
        this.clazz = clazz;
        System.out.println("------------------------分割线------------------------");
        userId = id;
        random = new Random();
    }
    @Override
    public void run() {
        try {
            log.info("desktopid,{}",userId);
            getUser();
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
            Test.test(userId,method,clazz);
        } catch (Exception e) {
            log.error("{}",e);
        }
    }

    private void getUser(){

        User u = null;
        try {
            u = (User)method.invoke(clazz,userId);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if(u != null){
            log.info("User{}",u.toString());
        }else {
            log.info("User = null{}");
        }
    }
}
