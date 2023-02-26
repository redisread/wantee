package com.jiahongw.wantee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
@MapperScan("com.jiahongw.wantee.mapper")
public class WanteeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WanteeApplication.class, args);
    }

}
