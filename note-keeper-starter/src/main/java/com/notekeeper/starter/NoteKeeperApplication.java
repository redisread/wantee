package com.notekeeper.starter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.retry.annotation.EnableRetry;


/**
 * @author Redisread
 * @date 2024/12/8
 */
@SpringBootApplication
@EnableRetry
@MapperScan("com.notekeeper.infrastructure.dal.mapper")
@ComponentScan(basePackages = {"com.notekeeper"})
public class NoteKeeperApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoteKeeperApplication.class, args);
    }

}
