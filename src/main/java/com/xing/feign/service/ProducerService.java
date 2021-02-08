package com.xing.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "producer-service",fallback = ProducerServiceFB.class)
public interface ProducerService {
    @PostMapping("/send")
    public String kafka(@RequestParam("id") String id, @RequestParam("name") String name);

}
