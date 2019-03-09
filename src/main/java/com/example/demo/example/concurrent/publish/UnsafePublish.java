package com.example.demo.example.concurrent.publish;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class UnsafePublish {
    private String[] states = {"a","b","c"};
    public String[] getStates(){
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "d";
        log.info("{}",Arrays.toString(unsafePublish.states));

        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>(16);
        concurrentHashMap.put("a","a");
        concurrentHashMap.size();

        HashMap<String,String> hashMap = new HashMap<>(16);
        hashMap.put("a","a");


        Hashtable<String,String> hashtable = new Hashtable<>();
        hashtable.put(null,"a");
    }
}
