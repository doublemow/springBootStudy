package com.example.demo.example.concurrent.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@Slf4j
public class CyclicBarrierTest {
    public static void main(String[] args) {
        int n = 4;
        CyclicBarrier barrier= new CyclicBarrier(n);
        for(int i=0;i<n;i++){
            new Writer(barrier).start();
        }
    }
    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;
        private Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            log.info("线程{}{}",Thread.currentThread().getName(),"正在写入数据....");
            try {
                //以睡眠来模拟写入数据操作
                Thread.sleep(5000);
                log.info("线程{}{}",Thread.currentThread().getName(),"写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch(BrokenBarrierException e){
                e.printStackTrace();
            }
            log.info("所有线程写入完毕，继续处理其他任务...");
        }
    }
}
