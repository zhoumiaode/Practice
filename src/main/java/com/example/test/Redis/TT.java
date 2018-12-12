package com.example.test.Redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.text.SimpleDateFormat;

/**
 * @ProjectName: test
 * @Package: com.example.test.Redis
 * @ClassName: TT
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/21 16:43
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/21 16:43
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class TT {

    public static void main(String[] args){
        int  Lock_Timeout=3;
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long a=System.currentTimeMillis();
        System.out.println(a);
        String dateStr = dateformat.format(a);
        System.out.println(dateStr);
        long ab=a+Long.valueOf("30000");
        System.out.println(ab);
        String dateStr1 = dateformat.format(ab);
        System.out.println(dateStr1);
        long currentTime = System.currentTimeMillis();//当前时间
        System.out.println(currentTime);
        String lockTimeDuration = String.valueOf(currentTime + Lock_Timeout + 1);//锁的持续时间
        System.out.println(lockTimeDuration);
        JedisPoolConfig config=new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(200);
        // 设置最大空闲数
        config.setMaxIdle(8);
        // 设置最大等待时间
        config.setMaxWaitMillis(1000 * 1000);
        // 在borrow一个jedis实例时，是否需要验证，若为true，则所有jedis实例均是可用的
        config.setTestOnBorrow(true);
        JedisPool pool = new JedisPool(config, "127.0.0.1", 6379, 3000);
        Jedis jedis=pool.getResource();
        String aa=jedis.get("as");
        System.out.println(aa);

    }

    public static boolean method(){

        while (true){
            System.out.println(1);
            return true;
        }
    }
}
