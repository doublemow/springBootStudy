package com.example.demo.example.concurrent.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.*;

@Slf4j
public class ExAtomicInteger {

    // 请求总数
    private static int clientTotal = 5000;

    // 同时并发执行的线程数
    private static  int threadTotal = 200;

    private static AtomicInteger count = new AtomicInteger(0);
    private static AtomicLong atomicLong = new AtomicLong(0L);
    private static AtomicBoolean atomicBoolean = new AtomicBoolean(true);
    private static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(6);
    private static LongAdder longAdder = new LongAdder();
    private static DoubleAdder doubleAdder = new DoubleAdder();

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
        log.info("atomicLong:{}",atomicLong);
    }

    private static void add(){
        count.incrementAndGet();
        atomicLong.incrementAndGet();
        atomicBoolean.compareAndSet(true,false);
        atomicIntegerArray.incrementAndGet(1);
        longAdder.increment();
        doubleAdder.add(1.0D);
        // count.getAndIncrement();
    }
}
