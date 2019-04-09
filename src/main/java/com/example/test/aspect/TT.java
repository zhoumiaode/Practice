package com.example.test.aspect;

public class TT {
    public static void main(String[] args){
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread.run();
        System.out.print("ping");
    }

    static void mm(){
        System.out.print("pong");
    }
}
