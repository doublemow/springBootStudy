package com.example.demo.example.async.rxjava;

import io.reactivex.Flowable;

import java.util.ArrayList;
import java.util.List;

public class RpcCallSynchronize {
    public static String rpcCall(String ip, String param){
        System.out.println(Thread.currentThread().getName() + "" + ip + "rpcCall:" + param);
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return param;
    }

    public static void main(String[] args) {
        List<String> ipList = new ArrayList<>();

        for(int i = 1; i <= 10; ++i){
            ipList.add("192.168.0." + i);
        }

        //顺序调用
        long start = System.currentTimeMillis();
        Flowable.fromArray(ipList.toArray(new String[0]))
                .map(v -> rpcCall(v, v))
                .subscribe(System.out::println);

        System.out.println("cost:" + (System.currentTimeMillis() - start));
    }

}







