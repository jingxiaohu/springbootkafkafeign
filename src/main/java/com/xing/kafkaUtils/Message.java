package com.xing.kafkaUtils;

import lombok.Data;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Date;

@Data
public class Message {
    String id;
    String uuid;
    String name;
    String time;

}
