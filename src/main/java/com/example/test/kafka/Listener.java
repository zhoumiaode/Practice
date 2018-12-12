package com.example.test.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @ProjectName: test
 * @Package: com.example.test.kafka
 * @ClassName: Listener
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/12/11 13:14
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/12/11 13:14
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Component
public class Listener {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @KafkaListener(topicPartitions = {
            @TopicPartition(topic = "mythree",partitions = "1")
    })
    public void listen(ConsumerRecord record,Acknowledgment ack) {
        logger.info("kafka的key: " + record.key());
        logger.info("kafka的value: " + record.value());
        logger.info("kafka的offset: " + record.offset());
        ack.acknowledge();
    }
    }

