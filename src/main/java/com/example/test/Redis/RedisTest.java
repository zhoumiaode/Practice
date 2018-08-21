package com.example.test.Redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.awt.print.Pageable;

/**
 * @ProjectName: test
 * @Package: com.example.test.Redis
 * @ClassName: RedisTest
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/21 16:35
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/21 16:35
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class RedisTest {
    private JedisPool pool;

    public RedisTest(JedisPool pool){
        this.pool=pool;
    }

    /** 
    * @Description: 加锁，其中key表示锁的名称，timeout表示超时时间
    * @Param: [key, timeout]
    * @return: void 
    * @Author: zhoumiaode
    * @Date: 2018/08/21 
    */ 
    public boolean tryLock(String key,long timeout) throws InterruptedException {

        Jedis conn=pool.getResource();
        //获取当前时间
        long time=System.currentTimeMillis();
        while(true){
            //返回1表示获取锁成功
            if(conn.setnx(key,String.valueOf(time+timeout))==1){
                //conn.expire
                return true;
            }else{
                //获取锁的生存时间
                long time1=getExpire(key);
                //锁的生存时间小于当前时间，则表示锁已经失效
                if(time1-time<0){
                    //重新设置锁的生存时间,返回旧的生存时间
                    String a=conn.getSet(key,String.valueOf(time1+timeout));
                    //如果旧的生存时间<当前时间，则表示我是第一个进行getset操作，我可以拿到锁，另一种那返回的锁的时候和上面一开始getExpire()
                    //方法获得的时候进行比较，如果相同则表示我是第一个getset的，不相同则表示在我之前已经有人进行过getset操作，那么我只能重新请求。
                    if(Long.parseLong(a)-time<0){
                        return true;
                    }else{
                        System.out.println("正在尝试重新获取锁中");
                        Thread.sleep(1000);
                    }
                }
            }
        }
    }

    /** 
    * @Description:获取指定key的生存时间
    * @Param: [key] 
    * @return: long 
    * @Author: zhoumiaode
    * @Date: 2018/08/21 
    */ 
    public long getExpire(String key){
        Jedis conn=pool.getResource();
        long expire=0;
        if(conn.exists(key)){
            expire=Long.parseLong(conn.get(key));
        }
        return expire;
    }

    /** 
    * @Description:  锁释放
    * @Param: [key] 
    * @return: void 
    * @Author: zhoumiaode
    * @Date: 2018/08/21 
    */ 
    public void unlock(String key){
        Jedis conn= pool.getResource();
        //获取当前时间
        long time=System.currentTimeMillis();
        if(conn.exists(key)){
            //获取锁的时间
            String time1=conn.get(key);
            //如果生存时间大于当前时间，则进行释放锁的操作,如果生存时间小于当前时间，则表示锁已经失效，那么可能已经被别人所占有，则不做操作
            if(Long.valueOf(time1)-time>0){
                conn.del(key);
            }
        }
    }


    /** 
    * @Description:测试拿到锁后的执行方法
    * @Param: [] 
    * @return: void 
    * @Author: zhoumiaode
    * @Date: 2018/08/21 
    */ 
    public void method(){
        System.out.println("我已经得到锁了，我正在处理自己的事情");
    }
}
