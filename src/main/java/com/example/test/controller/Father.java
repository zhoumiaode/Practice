package com.example.test.controller;

public class Father {

    static {
        System.out.println("Father static");
    }

    {
        System.out.println("Father not static");
    }

    public Father(){
        System.out.println("Father 构造函数");
    }

    public void method(){
        System.out.println("Father Method");
    }

    public void method1(){

    }
}
