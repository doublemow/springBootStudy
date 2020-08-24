package com.example.demo.example.async.rxjava;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用observeOn方法来让rpcCall的执行由main函数所在线程切换到IO线程，以便让main函数所在线程及时释放出来。
 * 但是在io线程里面这10个任务还是顺序执行的
 */
public class RpcCallSynchronizeIo {
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
                .observeOn(Schedulers.io())
                .map(v -> rpcCall(v, v))
                .subscribe(System.out::println);

        System.out.println("cost:" + (System.currentTimeMillis() - start));

        Thread.currentThread().join();
    }

}







