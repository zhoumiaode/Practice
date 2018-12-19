package com.example.test.kafka;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ProjectName: test
 * @Package: com.example.test.kafka
 * @ClassName: LockTest
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/12/14 11:23
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/12/14 11:23
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class LockTest {

    private Lock lock = new ReentrantLock();

    //需要参与同步的方法
    private void method(Thread thread){
/*      lock.lock();
       try {
           System.out.println("线程名"+thread.getName() + "获得了锁");
       }catch(Exception e){
           e.printStackTrace();
       } finally {
           System.out.println("线程名"+thread.getName() + "释放了锁");
           lock.unlock();
       }*/


        if(lock.tryLock()){
            try {
                System.out.println("线程名"+thread.getName() + "获得了锁");
            }catch(Exception e){
                e.printStackTrace();
            } finally {
                System.out.println("线程名"+thread.getName() + "释放了锁");
                lock.unlock();
            }
        }else{
            System.out.println("我是"+Thread.currentThread().getName()+"有人占着锁，我就不要啦");
        }
    }

    public static void main(String[] args) {
        LockTest lockTest = new LockTest();

        //线程1
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                lockTest.method(Thread.currentThread());
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                lockTest.method(Thread.currentThread());
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
