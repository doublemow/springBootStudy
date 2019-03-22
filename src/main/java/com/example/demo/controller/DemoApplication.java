package com.example.demo.controller;

import com.example.demo.example.concurrent.threadlocal.HttpInterceptor;
import com.example.demo.filter.HttpFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 启动类
 * @author chen qi
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class, scanBasePackages = "com.example.demo")
@ServletComponentScan
@MapperScan(basePackages = {"com.example.demo.dao"})
public class DemoApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean httpFilter(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new HttpFilter());
		filterRegistrationBean.addUrlPatterns("/threadLocal/*");
		return filterRegistrationBean;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/threadLocal/*");
	}
}
