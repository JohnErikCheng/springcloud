package com.cheng.erik.john.intface;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName ：HomeClient
 * @Author ：JohnErikCheng
 * @Email ：dong@19910925@126.com
 * @Date ：Created in 2019/5/29 16:02
 * @Description:
 */
@FeignClient("ribbon-provider")
public interface HomeClient {
    @GetMapping("/ribbon")
    String consumer();
}
