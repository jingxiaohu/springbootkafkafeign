package com.xing.kafkaUtils;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class KafkaProducer {
    @Autowired
    private KafkaSendResultHandler producerListener;
    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;
    public void  sendMessage(Message message){
        kafkaTemplate.setProducerListener(producerListener);
        //使用ProducerRecord发送消息
        ProducerRecord record = new ProducerRecord("test", "key001",ObjectMapperUtil.toJson(message));
        kafkaTemplate.send(record);

//        this.kafkaTemplate.send("test0","key001",ObjectMapperUtil.toJson(message)).;
    }
}
