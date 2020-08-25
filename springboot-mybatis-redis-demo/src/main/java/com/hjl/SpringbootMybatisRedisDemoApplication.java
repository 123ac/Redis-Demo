package com.hjl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.hjl.dao")
@ComponentScan(basePackages = {"com.hjl.*"})
public class SpringbootMybatisRedisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisRedisDemoApplication.class, args);
    }

}
