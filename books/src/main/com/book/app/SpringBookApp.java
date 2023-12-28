package com.book.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//说明该工程是一个SpringBoot工程，可以启动内置的tomcat服务器
@SpringBootApplication
@MapperScan("com.book.app.dao")
@EnableTransactionManagement  //开启事务标签
public class SpringBookApp {
    public static void main(String[] args) {
        //启动内置tomcat服务器
        SpringApplication.run(SpringBookApp.class);
        System.out.println("愿神？启动！！");
    }
}



