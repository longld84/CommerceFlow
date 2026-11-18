package com.longld.commerceflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class CommerceflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommerceflowApplication.class, args);
    }

}
