package com.xing.feign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class FeignController {
    @Autowired
    private  ProducerService producerService;
    @PostMapping("/sendd")
    public String kafka(@RequestParam("id") String id, @RequestParam("name") String name){
        System.err.println("FeignController:::::::::::::::::::::::::::::");
        return producerService.kafka(id,name);
    }
}
