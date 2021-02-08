package com.xing.feign.service;

import org.springframework.stereotype.Component;

@Component
public class ProducerServiceFB implements ProducerService{
    @Override
    public String kafka(String id, String name) {
        return "提交失败";
    }
}
