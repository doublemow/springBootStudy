package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author a
 */
@Setter
@ToString
@Getter
public class User {
    String name;
    String age;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss a" ,timezone="GMT+8", locale = "ch")
    Date birthDay;
    @JsonFormat()
    String pwd;
    String grade;
}
