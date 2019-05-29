package com.cheng.erik.cheng.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ：TestController
 * @Author ：JohnErikCheng
 * @Email ：dong@19910925@126.com
 * @Date ：Created in 2019/4/30 14:00
 * @Description: 这是一个测试类。
 */
@RestController
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    /**
      * @Param:
      * @return: 
      * @Author: JohnErikCheng
      * @Email: dong19910925@126.com
      * @Date: 2019/4/30 14:20
      * @Description: 
      */
    @GetMapping("/test")
    public String test(Integer numA, Integer numB) {
        Integer result = null;
        try {
            result = numA + numB;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info("result is:" + result);
        return "Hello World!";
    }
}
