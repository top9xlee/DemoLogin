package com.Website.Step2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class Step2Application {

    public static void main(String[] args) {
        SpringApplication.run(Step2Application.class, args);
    }

}
