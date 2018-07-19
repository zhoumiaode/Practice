package com.example.test.test;

public class Bank {

    private static int i=0;


    public void save(int money){
        i=i+money;
        System.out.println("存钱操作,账户余额:"+i);

    }

    public void get(int money){
        if((i-money)<0){
            System.out.println("余额不足");
        }
        i=i-money;
        System.out.println("取钱操作,账户余额:"+i);
    }
}
