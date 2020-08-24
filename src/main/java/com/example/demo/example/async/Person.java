package com.example.demo.example.async;

import lombok.Data;

@Data
public class Person {
    private int age;

    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
