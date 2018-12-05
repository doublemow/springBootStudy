package com.example.demo.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 启动类
 * @author chen qi
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class, scanBasePackages = "com.example.demo")
@ServletComponentScan
@MapperScan(basePackages = {"com.example.demo.dao"})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
