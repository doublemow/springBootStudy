package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chen qi
 */
@RestController
@Slf4j
public class HelloController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello,Spring Boot";
    }

    @RequestMapping(value = "/getUserById")
    public User getUserById(){
        User u = userService.getById(1L);
        log.debug("------------------debug-----------------");
        log.info("-------------------info-------------------");
        log.error("==================error==================");
        return u;
    }

    public static void main(String[] args) {

    }
}
