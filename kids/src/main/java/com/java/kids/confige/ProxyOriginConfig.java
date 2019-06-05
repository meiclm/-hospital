package com.java.kids.confige;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class ProxyOriginConfig {
    //跨域代理，前端请求后端的代理
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许跨域访问的域名
        corsConfiguration.addAllowedOrigin("*");
        // 请求头
        corsConfiguration.addAllowedHeader("*");
        // 请求方法
        corsConfiguration.addAllowedMethod("*");//所有的方法
//        corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
//        corsConfiguration.addAllowedMethod(HttpMethod.POST);
//        corsConfiguration.addAllowedMethod(HttpMethod.GET);
//        corsConfiguration.addAllowedMethod(HttpMethod.PUT);
//        corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
//        corsConfiguration.addAllowedMethod(HttpMethod.OPTIONS);
        return corsConfiguration;
    }
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}
