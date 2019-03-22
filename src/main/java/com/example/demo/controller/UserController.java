package com.example.demo.controller;

import com.example.demo.cache.service.RedisService;
import com.example.demo.constant.Events;
import com.example.demo.constant.States;
import com.example.demo.example.concurrent.threadlocal.RequestHolder;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.test.Test;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.*;

/**
 * @author chen qi
 */
@RestController
@Slf4j
public class UserController {

    private UserService userService;
    private StateMachine<States, Events> stateMachine;
    private RedisService redisService;
    @Autowired
    public UserController(StateMachine<States, Events> stateMachine, UserService userService, RedisService redisService){
        this.stateMachine = stateMachine;
        this.userService = userService;
        this.redisService = redisService;
    }

    @RequestMapping(value = "/createUser")
    public String hello(String name, Long id) {
        User u = new User();
        u.setName(name);
        u.setId(id);
        try{
            log.info("set redis name{}",u.getName());
            redisService.set("user",u);
            userService.insert(u);
        }catch (Exception e){
            log.info("添加失败",e);
            return "failed";
        }
        return "success";
    }

    @RequestMapping(value = "/threadLocal/getUserById")
    public User getThreadLocalUserById(Long id){
        User u = (User) RequestHolder.get();

        if(u == null){
            u = userService.getById(id);
        }
        return u;
    }

    @RequestMapping(value = "/getUserById")
    public User getUserById(Long id){
        User u = (User) RequestHolder.get();

        if(u == null){
            u = userService.getById(id);
        }

        Method method = null;
        try {
            method = userService.getClass().getMethod("getById", Long.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if(method != null){
            Test.test(id,method,userService);
        }
        return u;
    }

    /**
     * 测试状态机
     */
    @RequestMapping(value = "/testMachine")
    public void testMachine() {
        stateMachine.start();
        stateMachine.sendEvent(Events.PAY);
        stateMachine.sendEvent(Events.RECEIVE);
    }

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("Hello !!");
        ScheduledExecutorService service =  Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
        service.shutdown();
        service.scheduleAtFixedRate(runnable,0,2,TimeUnit.DAYS);
    }
}
