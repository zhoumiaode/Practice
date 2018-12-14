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
 * @Description: kafkaProducer配置Bean
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
    private  String bootstrap_Servers;

    @Value(value = "${acks}")
    private  String acks;

    @Value(value = "${retries}")
    private  String retries;

    @Value(value = "${batch.size}")
    private  String batch_size;

    @Value(value = "${linger.ms}")
    private  String linger_ms;

    @Value(value = "${buffer.memory}")
    private  String buffer_memory;

    @Value(value = "${key.serializer}")
    private  String  key_serializer;

    @Value(value = "${value.serializer}")
    private  String  value_serializer;

    @Bean
    public Properties properties(){
        Properties props=new Properties();
        props.put("bootstrap.servers", bootstrap_Servers);
        props.put("acks", acks);
        props.put("retries",retries);
        props.put("batch.size", batch_size);
        props.put("linger.ms", linger_ms);
        props.put("buffer.memory", buffer_memory);
        props.put("key.serializer", key_serializer);
        props.put("value.serializer", value_serializer);
        String a="";
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
