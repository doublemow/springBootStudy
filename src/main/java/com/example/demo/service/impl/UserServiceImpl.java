package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class UserServiceImpl implements UserService,Serializable {

    private static final long serialVersionUID = 1L;

    public UserServiceImpl(){

    }

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getById(Long id) {
        User user = userMapper.selectById(id);
        return user;
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public User getByMethodName(String methodName) {
        User user = userMapper.selectById(1L);
        return user;
    }
}
