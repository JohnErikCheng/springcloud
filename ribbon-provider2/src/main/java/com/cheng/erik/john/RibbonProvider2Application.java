package com.cheng.erik.john;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RibbonProvider2Application {

    public static void main(String[] args) {
        SpringApplication.run(RibbonProvider2Application.class, args);
    }

}
