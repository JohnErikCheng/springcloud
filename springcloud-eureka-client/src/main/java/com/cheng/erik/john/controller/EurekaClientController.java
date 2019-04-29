package com.cheng.erik.john.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：JohnErikCheng
 * @date ：Created in 2019/4/26 11:22
 * @description：
 * @version: 1.0
 */
@RestController
public class EurekaClientController {
    private static final Logger logger = LoggerFactory.getLogger(EurekaClientController.class);

    @Autowired
    private DiscoveryClient client;

    @GetMapping("/add")
    public Integer addCal(@RequestParam Integer paramA, @RequestParam Integer paramB) {
        logger.info("打印日志测试...");
        return  paramA + paramB;
    }
}
