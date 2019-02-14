package com.github.lisdocument.dubbo.service;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

@Component
public class TestConsumerService {

    @Reference
    private TestService testService;

    public String test(){
        return testService.test();
    }
}
