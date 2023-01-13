package com.jiahongw.wantee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableRetry
public class WanteeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WanteeApplication.class, args);
    }

}
