package com.example.demo.controller;

import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author chen qi
 */
@RestController
@Slf4j
public class HelloController {
    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello,Spring Boot";
    }

    @RequestMapping(value = "/getUser")
    public User getUser(){
        User u = new User();
        u.setAge("18");
        u.setName("dake");
        u.setPwd("234");
        u.setBirthDay(new Date());
        u.setGrade("90");
        log.debug("------------------debug-----------------");
        log.info("-------------------info-------------------");
        log.error("==================error==================");
        return u;
    }

    public static void main(String[] args) {

        User u = new User();
        u.setAge("18");
        u.setName("dake");
        u.setPwd("234");
        u.setBirthDay(new Date());
        u.setGrade("90");

        System.out.println(u.getPwd());
    }
}
