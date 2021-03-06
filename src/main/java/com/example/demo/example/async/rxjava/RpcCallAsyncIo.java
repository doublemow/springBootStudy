package com.example.demo.example.async.rxjava;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

/**
 * 异步
 */
public class RpcCallAsyncIo {
    public static String rpcCall(String ip, String param){
        System.out.println(Thread.currentThread().getName() + "" + ip + "rpcCall:" + param);
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return param;
    }

    public static void main(String[] args) throws InterruptedException {
        List<String> ipList = new ArrayList<>();

        for(int i = 1; i <= 10; ++i){
            ipList.add("192.168.0." + i);
        }

        //顺序调用
        long start = System.currentTimeMillis();
        Flowable.fromArray(ipList.toArray(new String[0]))
                .flatMap(ip ->
                        Flowable.just(ip)
                            .subscribeOn(Schedulers.io())
                            .map(v -> rpcCall(v,v)))
                .blockingSubscribe(System.out::println);

        System.out.println("cost:" + (System.currentTimeMillis() - start));

    }

}







