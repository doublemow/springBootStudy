package com.example.demo.controller;

import com.example.demo.constant.Events;
import com.example.demo.constant.States;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @author chen qi
 */
@RestController
@Slf4j
public class HelloController{

    private UserService userService;
    private StateMachine<States, Events> stateMachine;
    private RedisTemplate redisTemplate;
    @Autowired
    public HelloController(StateMachine<States, Events> stateMachine, UserService userService,
                           RedisTemplate redisTemplate){
        this.stateMachine = stateMachine;
        this.userService = userService;
        this.redisTemplate = redisTemplate;
    }

    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello,Spring Boot";
    }

    @RequestMapping(value = "/getUserById")
    public User getUserById(){
        User u = userService.getById(1L);
        redisTemplate.opsForValue().set("test:set","testValue1");
        return u;
    }

    @RequestMapping(value = "/testMachine")
    public void testMachine() {
        stateMachine.start();
        stateMachine.sendEvent(Events.PAY);
        stateMachine.sendEvent(Events.RECEIVE);
    }

    public static void main(String[] args) {
        String[] stringArray = new String[5];
        ArrayUtils.add(stringArray,1,"a");
        ArrayUtils.contains(stringArray,"a");
        ArrayList<String> stringList = new ArrayList<>();
        stringList.contains("a");
        stringList.add("a");
    }
}
