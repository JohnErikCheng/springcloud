package com.cheng.erik.cheng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ：JohnErikCheng
 * @date ：Created in 2019/4/29 16:00
 * @description：
 * @version: 1.0
 */
@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/add")
    public String add() {
        return restTemplate.getForEntity("http://SPRINGCLOUD-EUREKA-CLIENT/add?a=10&b=20",String.class).getBody();
    }
}
