package com.example.demo.concurrent.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class CountExample2 {

    // 请求总数
    public static  int clientTotal = 5000;

    // 同时并发执行的线程数
    public static  int threadTotal = 200;

    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        //同时并发执行的线程数
        final Semaphore semaphore = new Semaphore(threadTotal);
        //请求总数
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0; i < clientTotal; i++){
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("InterruptedException",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        log.info("count:{}", count);
    }

    private static void add(){
        count.incrementAndGet();
        // count.getAndIncrement();
    }
}
