package com.example.test.test;

public class Test2 {

    public static void main(String[] args) {
        Test3 test3=new Test3();
        for (int i = 0; i < 10; i++) {
            MyThread MyThread = new MyThread(test3);
            MyThread.start();
        }
    }
}
    class MyThread extends  Thread {

        Test3 test3;

        public MyThread(Test3 testObject) {
            this.test3 = testObject;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {


                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "--"+test3.setID_02());
            System.out.println(Thread.currentThread().getName() + "--"+test3.setID_01());
        }
    }
