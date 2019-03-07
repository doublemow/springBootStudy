package com.example.demo.controller;

import com.example.demo.cache.service.RedisService;
import com.example.demo.constant.Events;
import com.example.demo.constant.States;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chen qi
 */
@RestController
@Slf4j
public class HelloController{

    private UserService userService;
    private StateMachine<States, Events> stateMachine;
    private RedisService redisService;
    @Autowired
    public HelloController(StateMachine<States, Events> stateMachine, UserService userService,RedisService redisService){
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

    @RequestMapping(value = "/getUser")
    public User getUserById(){
        User u = redisService.get("user",User.class);
        return u;
    }

    @RequestMapping(value = "/getUserById")
    public User getUserById(Long id){
        User u = userService.getById(id);
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
        User u = new User();
        u.setName("name");
        u.setId(1L);

    }
}
