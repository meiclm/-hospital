package com.java.kids;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//扫描到所有的dao包下的mapper类
@MapperScan("com.java.kids.dao")

@SpringBootApplication
public class KidsApplication {

    public static void main(String[] args) {
        SpringApplication.run(KidsApplication.class, args);
    }

}
