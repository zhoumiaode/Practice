package com.example.test.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.redis.connection.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.listener.MessageListener;

/**
 * @ProjectName: test
 * @Package: com.example.test.kafka
 * @ClassName: M
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/12/07 13:47
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/12/07 13:47
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class M implements org.springframework.kafka.listener.MessageListener<Integer, String> {
    public void onMessage(ConsumerRecord<Integer, String> consumerRecord) {
        System.out.println("aa'");
        Object o = consumerRecord.value();
        System.out.println(String.valueOf(o));
    }

}
