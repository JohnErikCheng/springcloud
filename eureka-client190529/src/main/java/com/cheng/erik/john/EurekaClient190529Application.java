package com.cheng.erik.john;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaClient190529Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClient190529Application.class, args);
    }

}
