package com.example.test.kafka;

import org.apache.kafka.clients.producer.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Properties;

/**
 * @ProjectName: test
 * @Package: com.example.test.kafka
 * @ClassName: ProduceDemo
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/12/06 14:21
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/12/06 14:21
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class ProduceDemo {
    private static String zookerUrl="localhost:2181";
    private static String kafkaUrl="localhost:9092";
    private static String Topic="springMVC";
    public static void main(String[] args){
        Properties props = new Properties();
        props.put("bootstrap.servers", kafkaUrl);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String,String> producer = new KafkaProducer<String,String>(props);
        for(int i = 0; i < 10; i++)
            producer.send(new ProducerRecord<String, String>("foo", Integer.toString(i), Integer.toString(i)), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if(e!=null){
                        e.printStackTrace();

                    }else{
                        System.out.println(recordMetadata.offset());
                        System.out.println(recordMetadata.topic());
                        System.out.println(recordMetadata.partition());
                        System.out.println(recordMetadata.serializedKeySize());
                    }
                }
            });

        producer.close();
    }
}
