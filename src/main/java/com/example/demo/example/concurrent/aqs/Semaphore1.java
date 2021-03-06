package com.example.demo.example.concurrent.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class Semaphore1 {

    // 请求总数
    public static  int clientTotal = 5000;

    // 同时并发执行的线程数
    public static  int threadTotal = 200;

    public static int count = 0;

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0; i < clientTotal; i++){
            executorService.execute(() -> {
                try {
                    if(semaphore.tryAcquire(1, TimeUnit.SECONDS)){
                        semaphore.acquire();
                        add();
                        semaphore.release();
                    }
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
        count++;
    }
}
