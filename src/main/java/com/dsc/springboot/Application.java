package com.dsc.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ConfigurationProperties(prefix = "spring.datasource")
@MapperScan(value = "com.dsc.springboot.mapper.*")
@EnableTransactionManagement
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
