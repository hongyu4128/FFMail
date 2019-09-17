package com.yhhy.FFMail.recv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.yhhy.FFMail.recv.common.SpringContext;

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
public class RecvApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(RecvApp.class, args);
        SpringContext.setApplicationContext(applicationContext);
    }
}
