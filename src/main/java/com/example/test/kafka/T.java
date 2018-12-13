package com.example.test.kafka;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;

/**
 * @ProjectName: test
 * @Package: com.example.test.kafka
 * @ClassName: T
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/12/07 11:03
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/12/07 11:03
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class T implements ProducerListener {
    @Override
    public void onSuccess(String s, Integer integer, Object o, Object o2, RecordMetadata recordMetadata) {

        String a="{\"1\":\"1\"}";
    }

    @Override
    public void onError(String s, Integer integer, Object o, Object o2, Exception e) {

    }

    @Override
    public boolean isInterestedInSuccess() {
        return false;
    }
}
