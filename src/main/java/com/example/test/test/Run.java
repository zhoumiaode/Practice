package com.example.test.test;

public class Run {
    public static void main(String[] args) {

        final TestSynchronized test = new TestSynchronized();
        final TestSynchronized test1 = new TestSynchronized();
        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                test.minus2();
            }
        });

        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                test1.minus2();
            }
        });

        thread1.start();
        thread2.start();

    }
}
