package com.jiahongw.wantee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class WanteeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WanteeApplication.class, args);
    }

}
