package com.tfb.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.TimeZone;

@SpringBootApplication
@MapperScan(basePackages = "com.tfb.project.mapper")
public class StartApp {
    public static void main(String[] args) {
        //设置默认时区
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        SpringApplication.run(StartApp.class, args);
    }
}
