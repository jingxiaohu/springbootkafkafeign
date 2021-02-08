package com.xing.kafkaUtils;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class KafkaConsumer {
    @Autowired
    private MongoTemplate mongoTemplate;

//    @KafkaListener(topics = "test0",groupId = "test-consumer-group")
    @KafkaListener(topics = "test",groupId = "test-consumer-group")
    public void KafkaMsg(ConsumerRecord<String,String> consumerRecord) {
        System.err.println("KafkaConsumer:::::::::::::::ConsumerRecord::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");

        System.err.println("kafka打印的信息如下：");
        //主题
        String topic=consumerRecord.topic();
        String key=consumerRecord.key();
        //消息内容
        String valus=consumerRecord.value();
        //分区
        int partition=consumerRecord.partition();
        //时间戳
        long timestamp=consumerRecord.timestamp();
        System.out.println("-------------------------------------------------------------------");
        System.out.println("------------------------------KafkaConsumer------2--------Mongo-----------------------");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("topic:"+topic);
        System.out.println("key:"+key);
        System.out.println("values:"+valus);
        System.out.println("partition:"+partition);
        System.out.println("timestamp:"+timestamp);
        Message ms=ObjectMapperUtil.toObject(valus,Message.class);
        try {
            mongoTemplate.save(ms,"data");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
