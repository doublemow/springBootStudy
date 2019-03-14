package com.example.demo.example.concurrent.threadlocal;

import lombok.extern.slf4j.Slf4j;

/**
 * @author cq
 */
@Slf4j
public class RequestHolder {
    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id){
        requestHolder.set(id);
    }

    public static Long get(){
        return requestHolder.get();
    }

    public static void remove(){
        requestHolder.remove();
    }
}
