package com.xing.controller;

import com.xing.kafkaUtils.Message;
import com.xing.kafkaUtils.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
@RestController
public class KafkaController {
    @Autowired
    private KafkaProducer kafkaProducer;
    @PostMapping("/send")
    public String kafka(@RequestParam("id") String id,@RequestParam("name") String name){
        System.err.println("KafkaController:::::::::::::::kafkaProducer::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        Date newTime = new Date();
        //设置时间格式
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
           for (int i=0;i<30;i++){
               Message msg=new Message();
               msg.setId(id);
               String salt= UUID.randomUUID().toString();
               //加密
               SimpleHash sHash= new SimpleHash("MD5",msg.getId(), salt,1);
               msg.setUuid(sHash.toHex());
               msg.setName(name);
               msg.setTime(sdf2.format(newTime));
               this.kafkaProducer.sendMessage(msg);
           }
        }catch (Exception e)
        {
           log.error(e.getMessage());
        }
        return "执行成功！";
    }
}
