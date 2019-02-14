package com.github.lisdocument.dubbo.controller;

import com.github.lisdocument.dubbo.service.TestConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestConsumerController {

    @Autowired
    private TestConsumerService testConsumerService;

    @RequestMapping("test")
    public String test(){
        return testConsumerService.test();
    }
}
