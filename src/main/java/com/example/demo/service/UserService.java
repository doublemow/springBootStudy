package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {

    public User getById(Long id);

    public void insert(User user);

    public User getByMethodName(String methodName);
}
