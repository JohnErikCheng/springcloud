package com.cheng.erik.john.controller;

import com.cheng.erik.john.intface.HomeClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName ：HystrixClientFallbackFactory
 * @Author ：JohnErikCheng
 * @Email ：dong@19910925@126.com
 * @Date ：Created in 2019/5/29 17:11
 * @Description:
 */
@Component
public class HystrixClientFallbackFactory implements FallbackFactory<HomeClient> {

    @Override
    public HomeClient create(Throwable throwable) {
        return () -> "feign + hystrix ,提供者服务挂了";
    }
}
