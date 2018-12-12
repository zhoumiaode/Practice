package com.example.test.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.Properties;

/**
 * @ProjectName: test
 * @Package: com.example.test.kafka
 * @ClassName: KafkaProducerConfig
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/12/11 11:09
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/12/11 11:09
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Configuration
@PropertySource("classpath:kafka.properties")
@EnableKafka
public class KafkaProducerConfig {


    @Value(value = "${bootstrap.servers}")
    private static String bootstrap_Servers;

    @Value(value = "${acks}")
    private static String acks;

    @Value(value = "${retries}")
    private static String retries;

    @Value(value = "${batch.size}")
    private static String batch_size;

    @Value(value = "${linger.ms}")
    private static String linger_ms;

    @Value(value = "${buffer.memory}")
    private static String buffer_memory;

    @Value(value = "${key.serializer}")
    private static String  key_serializer;

    @Value(value = "${value.serializer}")
    private static String  value_serializer;

    @Bean
    public Properties properties(){
        Properties props=new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return  props;
    }

    @Bean
    public DefaultKafkaProducerFactory defaultKafkaProducerFactory(){
        return  new DefaultKafkaProducerFactory(properties());
    }

    @Bean
    public KafkaTemplate kafkaTemplate(){
        KafkaTemplate kafkaTemplate=new KafkaTemplate(defaultKafkaProducerFactory());
        kafkaTemplate.setDefaultTopic("mythree");
        kafkaTemplate.setProducerListener(new KafkaProduceListener());
        return  kafkaTemplate;
    }
}
