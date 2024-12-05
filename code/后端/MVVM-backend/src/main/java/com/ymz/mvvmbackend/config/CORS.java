package com.ymz.mvvmbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CORS {
    @Bean
    public CorsFilter corsFilter() {
        // 创建 CORS 配置对象
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 允许携带凭证（如 cookies）
        config.addAllowedOriginPattern("*");//为方便，所以设置为允许所有的域
        config.addAllowedHeader("*"); // 允许所有头部
        config.addAllowedMethod("*"); // 允许所有方法（GET、POST 等）

        // 创建基于 URL 的 CORS 配置源
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // 应用配置到所有路径

        // 创建并返回 CorsFilter Bean
        return new CorsFilter(source);
    }
}
