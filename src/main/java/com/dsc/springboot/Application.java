package com.dsc.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.dsc"})
@MapperScan(value = "com.dsc.springboot.mapper.*")
@ConfigurationProperties(prefix = "spring.datasource")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
