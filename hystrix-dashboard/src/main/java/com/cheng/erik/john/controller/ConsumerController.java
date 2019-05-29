package com.cheng.erik.john.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName ：ConsumerController
 * @Author ：JohnErikCheng
 * @Email ：dong@19910925@126.com
 * @Date ：Created in 2019/5/29 15:15
 * @Description:
 */
@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultStores")
    @GetMapping("/hello")
    public String hello() {
        String body = restTemplate.getForEntity("http://ribbon-provider/ribbon", String.class).getBody();
        return body;
    }

    public String defaultStores() {
        return "Ribbon + hystrix ,提供者服务挂了";
    }
}
