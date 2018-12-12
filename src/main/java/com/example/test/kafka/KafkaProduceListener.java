package com.example.test.kafka;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: test
 * @Package: com.example.test.kafka
 * @ClassName: KafkaProduceListener
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/12/11 11:29
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/12/11 11:29
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Component
public class KafkaProduceListener implements ProducerListener {

    private static Logger log = LoggerFactory.getLogger( KafkaProduceListener.class );
    @Override
    public void onSuccess(String topic, Integer partition, Object key, Object value, RecordMetadata recordMetadata) {
              log.info("=======================kafka发送数据成功（日志）=======================");
              log.info("----------topic:"+topic);
              log.info("----------partition:"+partition);
              log.info("----------key:"+key);
              log.info("----------value:"+value);
              log.info("----------RecordMetadata:"+recordMetadata);
              log.info("~~~~~~~~~~kafka发送数据成功（日志结束）~~~~~~~~~~");
    }

    @Override
    public void onError(String topic, Integer partition, Object key, Object value, Exception e) {
        log.info("==========kafka发送数据错误（日志开始）==========");
          log.info("----------topic:"+topic);
          log.info("----------partition:"+partition);
          log.info("----------key:"+key);
           log.info("----------value:"+value);
           log.info("----------Exception:"+e);
           log.info("~~~~~~~~~~kafka发送数据错误（日志结束）~~~~~~~~~~");
           e.printStackTrace();

    }

    @Override
    public boolean isInterestedInSuccess() {
        //代表是否启动监听
        return true;
    }
}
