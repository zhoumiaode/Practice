package com.example.test.test.test;

import com.example.test.test.DemoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DemoPublish {
    @Autowired
    private ApplicationContext applicationContext;

    public void publish(String msg){
        applicationContext.publishEvent(new DemoEvent(this,msg));
    }
}
