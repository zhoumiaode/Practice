package com.example.test.test;

import com.example.test.utils.HttpClientUtil;
import net.sf.json.JSONObject;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ProjectName: test
 * @Package: com.example.test.test
 * @ClassName: TimeTest
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/25 10:07
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/25 10:07
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class TimeTest {

    public static void main(String[] args){
        ExecutorService service = Executors.newCachedThreadPool(); //创建一个线程池
        final CountDownLatch cdOrder = new CountDownLatch(1);//指挥官的命令，设置为1，指挥官一下达命令，则cutDown,变为0，战士们执行任务
        final CountDownLatch cdAnswer = new CountDownLatch(100);
        for(int i=0;i<3;i++){
            Runnable runnable = new Runnable(){
                public void run(){
                    try {
                        System.out.println("线程" + Thread.currentThread().getName() +
                                "正准备接受命令");
                        cdOrder.await(); //战士们都处于等待命令状态
                        System.out.println("线程" + Thread.currentThread().getName() +
                                "已接受命令");
                        JSONObject result=new JSONObject();
                        JSONObject json=new JSONObject();
                        json.put("id", "28");
                        result= HttpClientUtil.httpPost("http://192.168.89.136:8880/method", json, true);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }finally{
                        cdAnswer.countDown();
                    }
                }
            };
            service.execute(runnable);//为线程池添加任务
        }
        try {
            Thread.sleep((long)(Math.random()*10000));
            cdOrder.countDown(); //发送命令，cdOrder减1，处于等待的战士们停止等待转去执行任务。
            cdAnswer.await(); //命令发送后指挥官处于等待状态，一旦cdAnswer为0时停止等待继续往下执行
        } catch (Exception e) {
            e.printStackTrace();
        }
        service.shutdown(); //任务结束，停止线程池的所有线程
    }
}
