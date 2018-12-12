package com.example.test.Redis;

import com.example.test.utils.HttpClientUtil;
import net.sf.json.JSONObject;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ProjectName: Redis分布式锁的测试
 * @Package: com.example.test.Redis
 * @ClassName: RedisLockTest
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/21 18:04
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/21 18:04
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class RedisLockTest {

    public static final String KEY="lock";
    public static final long Time_Out=200;
    public static void main(String[] args){
        JedisPoolConfig config=new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(1100);
        // 设置最大空闲数
        config.setMaxIdle(200);
        // 设置最大等待时间
        config.setMaxWaitMillis(1000 * 1000);
        // 在borrow一个jedis实例时，是否需要验证，若为true，则所有jedis实例均是可用的
        config.setTestOnBorrow(true);
        JedisPool pool = new JedisPool(config, "127.0.0.1", 6379);
        ExecutorService service = Executors.newCachedThreadPool(); //创建一个线程池
        final CountDownLatch cdOrder = new CountDownLatch(1);//指挥官的命令，设置为1，指挥官一下达命令，则cutDown,变为0，战士们执行任务
        final CountDownLatch cdAnswer = new CountDownLatch(50);
        for(int i=0;i<50;i++){
            Runnable runnable = new Runnable(){
                public void run(){
                    RedisTest redisTest=new RedisTest(pool);
                    try {
                        System.out.println("线程" + Thread.currentThread().getName() +
                                "正准备接受命令");
                        cdOrder.await(); //战士们都处于等待命令状态
                        System.out.println("线程" + Thread.currentThread().getName() +
                                "已接受命令");
                        if(redisTest.tryLock(KEY,Time_Out)){
                            System.out.println("线程" + Thread.currentThread().getName()+redisTest.method());
                        }
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }finally{
                        redisTest.unlock(KEY);
                        cdAnswer.countDown();
                    }
                }
            };
            service.execute(runnable);//为线程池添加任务
        }
        try {

            Thread.sleep((long)(Math.random()*1000));
            cdOrder.countDown(); //发送命令，cdOrder减1，处于等待的战士们停止等待转去执行任务。
            cdAnswer.await(); //命令发送后指挥官处于等待状态，一旦cdAnswer为0时停止等待继续往下执行
        } catch (Exception e) {
            e.printStackTrace();
        }
        service.shutdown(); //任务结束，停止线程池的所有线程
        System.out.println("所有线程执行结束");
    }

}
