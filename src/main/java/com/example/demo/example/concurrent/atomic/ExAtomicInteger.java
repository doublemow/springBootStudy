package com.example.demo.example.concurrent.atomic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.*;
import java.util.function.LongBinaryOperator;

@Slf4j
public class ExAtomicInteger {

    // 请求总数
    private static int clientTotal = 5000;

    // 同时并发执行的线程数
    private static  int threadTotal = 200;

    @Getter
    private volatile int n = 1;

    private static ExAtomicInteger exAtomicInteger = new ExAtomicInteger();

    private static AtomicInteger count = new AtomicInteger(0);
    private static AtomicLong atomicLong = new AtomicLong(0L);
    private static AtomicBoolean atomicBoolean = new AtomicBoolean(true);
    private static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(6);
    private static LongAdder longAdder = new LongAdder();
    private static DoubleAdder doubleAdder = new DoubleAdder();
    private static LongAccumulator accumulator1 = new LongAccumulator(new LongBinaryOperator() {
        @Override
        public long applyAsLong(long left, long right) {
            return left + right;
        }
    }, 0);
    private static LongAccumulator accumulator = new LongAccumulator((left,right)->(left + right)
    , 0);
    private static AtomicReference<Integer> atomicReference = new AtomicReference<>(1);

    private static AtomicIntegerFieldUpdater<ExAtomicInteger> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(ExAtomicInteger.class,"n");

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
        log.info("longAdder:{}",longAdder.intValue());
        log.info("longAccumulator:{}",accumulator.intValue());
        log.info("atomicReference:{}",atomicReference);
        log.info("atomicIntegerFieldUpdater:{}",exAtomicInteger.getN());
    }

    private static void add(){
//        count.incrementAndGet();
//        atomicLong.incrementAndGet();
//        atomicBoolean.compareAndSet(true,false);
//        atomicIntegerArray.incrementAndGet(1);
//        longAdder.add(1);
//        doubleAdder.add(1.0D);
//        accumulator.accumulate(1);
        atomicReference.compareAndSet(1,2);
        atomicIntegerFieldUpdater.compareAndSet(exAtomicInteger,1,2);
        // count.getAndIncrement();
    }
}
