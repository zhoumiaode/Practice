package com.example.test.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ProjectName: test
 * @Package: com.example.test.kafka
 * @ClassName: KafkaController
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/12/11 11:35
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/12/11 11:35
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@RestController
public class KafkaController {

    private static Logger log = LoggerFactory.getLogger( KafkaController.class );

    @Resource
    private KafkaTemplate kafkaTemplate;

    @GetMapping(value = "getKafka")
    public String first(){
       // for(int i=0;i<10;i++){
            kafkaTemplate.sendDefault("hi");
        ListenableFuture<SendResult<Integer, String>> listenableFuture= kafkaTemplate.sendDefault("2");
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onFailure(Throwable ex) {

            }

            @Override
            public void onSuccess(SendResult<Integer, String> result) {

            }
        });
        //}
        return "success";
    }
}
