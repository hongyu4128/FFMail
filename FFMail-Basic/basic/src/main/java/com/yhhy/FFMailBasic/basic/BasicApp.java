package com.yhhy.FFMailBasic.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.yhhy.FFMailBasic.basic.common.SpringContext;

/**
 * Hello world!
 *
 */
@RestController
@Configuration
@EnableAutoConfiguration
@EnableDubboConfiguration
@ComponentScan
@SpringBootApplication
public class BasicApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(BasicApp.class, args);
        SpringContext.setApplicationContext(applicationContext);
    }
}
