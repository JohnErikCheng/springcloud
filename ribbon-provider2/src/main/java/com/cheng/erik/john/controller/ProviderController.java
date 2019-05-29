package com.cheng.erik.john.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ：ProviderController
 * @Author ：JohnErikCheng
 * @Email ：dong@19910925@126.com
 * @Date ：Created in 2019/5/29 14:57
 * @Description: 服务提供方。
 */
@RestController
public class ProviderController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/ribbon")
    public String ribbon() {
        return "Hello world ,port:" + port;
    }
}
