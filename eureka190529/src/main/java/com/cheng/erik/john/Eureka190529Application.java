package com.cheng.erik.john;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Eureka190529Application {

    public static void main(String[] args) {
        SpringApplication.run(Eureka190529Application.class, args);
    }

}
