package com.example.test.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.Properties;

/**
 * @ProjectName: test
 * @Package: com.example.test.kafka
 * @ClassName: KafkaConsumerConfig
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/12/11 13:24
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/12/11 13:24
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    public KafkaConsumerConfig(){
        System.out.println("消费者初始化");
    }

    @Bean
    public Properties ConsumerProperties(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test3");
        props.put("enable.auto.commit", "false");
        props.put("max.poll.records","50");
        //props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return  props;
    }
    @Bean
    public ConsumerFactory<Integer, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory(ConsumerProperties());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL);
        return factory;
    }

    @Bean
    public Listener listener(){
        return new Listener();
    }
}
