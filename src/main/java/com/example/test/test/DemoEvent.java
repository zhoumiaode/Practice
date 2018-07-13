package com.example.test.test;


import org.springframework.context.ApplicationEvent;

public class DemoEvent extends ApplicationEvent{

    private static final long serialVersionUID=1L;
    private String msg;

    public DemoEvent(Object resource,String msg){
        super(resource);
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void st(){

    }
}
