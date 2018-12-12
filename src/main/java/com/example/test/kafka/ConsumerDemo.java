package com.example.test.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @ProjectName: test
 * @Package: com.example.test.kafka
 * @ClassName: ConsumerDemo
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/12/06 14:26
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/12/06 14:26
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class ConsumerDemo {

    public static void main(String[] args){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test1");
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        //设置读取的偏移量的指定起点,其中如果group.id是第一次的话，使用下面123句，否则注释掉123使用下面45或者46
       // Map<TopicPartition, OffsetAndMetadata> hashMaps = new HashMap<TopicPartition, OffsetAndMetadata>();
        //hashMaps.put(new TopicPartition("foo", 0), new OffsetAndMetadata(20));//1
        //consumer.commitSync(hashMaps);//2
        consumer.subscribe(Arrays.asList("foo"));//3
        //consumer.assign(Arrays.asList(new TopicPartition("foo", 0)));//4
        //consumer.seekToBeginning(Arrays.asList(new TopicPartition("foo", 0)));//不改变当前offset//5
       //consumer.seek(new TopicPartition("foo", 0), 10);//不改变当前offset//6

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            System.out.println(records.partitions());
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }
    }
}
