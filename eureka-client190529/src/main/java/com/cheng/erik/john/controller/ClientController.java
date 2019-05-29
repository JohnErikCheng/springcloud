package com.cheng.erik.john.controller;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeCounterGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ：ClientController
 * @Author ：JohnErikCheng
 * @Email ：dong@19910925@126.com
 * @Date ：Created in 2019/5/29 11:32
 * @Description:
 */
@RestController
public class ClientController {

    @GetMapping("/add")
    public Integer calc(@RequestParam Integer numA, @RequestParam Integer numB) {
        Integer result = numA + numB;
        return result;
    }
}
