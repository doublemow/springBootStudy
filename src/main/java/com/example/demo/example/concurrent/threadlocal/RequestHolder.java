package com.example.demo.example.concurrent.threadlocal;

import lombok.extern.slf4j.Slf4j;

/**
 * @author cq
 */
@Slf4j
public class RequestHolder {
    private final static ThreadLocal<Object> requestHolder = new ThreadLocal<>();

    public static void add(Object value){
        requestHolder.set(value);
    }

    public static Object get(){
        return requestHolder.get();
    }

    public static void remove(){
        requestHolder.remove();
    }
}
