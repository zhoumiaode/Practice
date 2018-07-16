package com.example.test.test;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DemoListen implements ApplicationListener<DemoEvent>{

    public void onApplicationEvent(DemoEvent event){

        String msg=event.getMsg();
        System.out.println("我(bean-DemoListen)接收到了(bean-DemoPublish)发布的消息:"+msg);
    }
}
