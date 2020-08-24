package com.example.demo.example.async.rxjava;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class RpcCallAsyncSubscribeOn {
    public static String rpcCall() throws InterruptedException {
        Thread.sleep(1000);
        String s = null;
        s.length();
        return "Done";
    }

    public static void main(String[] args) throws InterruptedException {
        //顺序调用
        long start = System.currentTimeMillis();

        Flowable.fromCallable(() -> {
            return rpcCall();
        }).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.single())
                .subscribe(System.out::println, Throwable::printStackTrace);
        System.out.println("cost:" + (System.currentTimeMillis() - start));

        Thread.sleep(2000);
    }

}







