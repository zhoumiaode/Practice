package com.example.test.controller.controller;

import java.util.Scanner;


public class TTest {

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入一个数组长度：");
        while(sc.hasNext()){
            int a=sc.nextInt();//输入一个正整数
            int b=sc.nextInt();//输入一个正整数
            int c=a+b;
            System.out.println("输入的数字是："+c);
        }
}

}
