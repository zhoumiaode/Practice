package com.example.test.controller;

public class Son extends Father{

    static {
        System.out.println("Son Static");
    }

    {
        System.out.println("Son not Static");
    }

    public Son(){
        System.out.println("Son 构造函数");
    }

    public void method(){
        System.out.println("Son的方法");
    }

    public static void main(String args[]){

        Son son=new Son();
        son.method();
    }
}
