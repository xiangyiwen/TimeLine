package com.example.timeline;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
//@MapperScan(basePackages = "com.example.timeline.Repository")
public class TimelineApplication {
    //public static  final CommentList commentList = new CommentList();
    public static void main(String[] args) {
        SpringApplication.run(TimelineApplication.class, args);
    }

}
