package com.example.demo.example.concurrent.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 */
@Slf4j
public class CountDownLatch1 {

    // 请求总数
    public static  int clientTotal = 20;

    public static int count = 0;

    final static CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i < clientTotal; i++){
            executorService.execute(() -> add());
        }
        countDownLatch.await(1, TimeUnit.SECONDS);
        log.info("count:{}", count);
    }

    private static void add(){
        countDownLatch.countDown();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
        log.info("{}",count);
    }
}
