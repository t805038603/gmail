package com.atguigu.gmall.gateway.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/*
    常用组件类注解：  可以创建对象注入到容器中
        spring+springmvc会自动扫描的注解：
        > Controller  Service  Repository Component
        > RestController ControllerAdvice  RestControllerAdvice
        > Configuration
        第三方的扫描bean的注解：
        >Mapper   MapperScan
        >FeignClient  EnableFeignClient
        >ServletComponentScan:扫描 tomcat的组件(filter  servlet  listener)
     springboot创建bean到容器中的方式：
        1、组件注解
        2、@Bean标注的方法返回值

 */
@Configuration
public class CorsConfig {
    /*
        跨域是为了保证后端接口资源安全
            允许跨域访问的 域名、请求方式、请求头、请求cookie 都可以限制
     */
    @Bean
    public CorsWebFilter corsWebFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://manager.gmall.com");//允许跨域访问的服务器域名
        config.addAllowedOrigin("http://api.gmall.com");
        config.addAllowedHeader("*");//允许跨域访问携带的请求头  * 代表所有
        config.addAllowedMethod("*");//允许跨域访问时的请求方式  * 代表所有
        config.setAllowCredentials(true); //允许跨域访问时携带cookie
        //给指定路径设置跨域参数： /**代表所有的请求
        source.registerCorsConfiguration("/**" , config);

        return new CorsWebFilter(source);
    }

}
