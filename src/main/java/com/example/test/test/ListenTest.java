package com.example.test.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ListenTest {

    public static void main(String args[]){

        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(ScopeConfig.class);
        DemoPublish publish= context.getBean(DemoPublish.class);
        publish.publish("hello word");
        context.close();
    }
}
