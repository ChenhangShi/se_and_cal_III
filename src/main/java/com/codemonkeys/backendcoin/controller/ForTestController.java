package com.codemonkeys.backendcoin.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 为了测试服务是否跑起来
 */
@RestController
@RequestMapping("test")
public class ForTestController {
    @GetMapping("/")
    public String test(){
        return "Running";
    }
}
