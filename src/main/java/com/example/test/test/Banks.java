package com.example.test.test;

public class Banks {

    public static void main(String[] args){

        Bank bank=new Bank();
        Thread thread1=new Thread(){
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    bank.save(100);
                    bank.get(100);
                    bank.look();
                    System.out.println("\n");
                }
            }
        };

        Thread thread2=new Thread(){
            @Override
            public void run() {
                while(true){
                    bank.get(100);
                    bank.save(100);
                    bank.look();
                    System.out.println("\n");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };

        thread1.start();
        thread2.start();
        thread1.stop();
        thread2.stop();

    }
}