package com.java.kids.confige;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//扩展mvc文件
@Configuration
public class MyConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        映射文件路径，将本地的路径F:/myResource/映射成localhost:8085/upload/**
        registry.addResourceHandler("/upload/**").addResourceLocations("file:F:/myDoctor/");
    }
}
