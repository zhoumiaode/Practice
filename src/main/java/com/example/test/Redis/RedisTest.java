package com.example.test.Redis;


import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

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
5
     .
     */
    public boolean tryLock(String key,long timeout) throws InterruptedException {

        Jedis conn=pool.getResource();
        //获取当前时间
        //long time=System.currentTimeMillis();
        while(true){
            //返回1表示获取锁成功
            if(conn.setnx(key,String.valueOf(System.currentTimeMillis()+timeout))==1){
                System.out.println(Thread.currentThread().getName()+"我拿到锁了");
                getExpire(key);
                conn.expire(key,5);
                return true;
            }

                //获取锁的生存时间
                String time1=getExpire(key);
                //锁的生存时间小于当前时间，则表示锁已经失效
                long time2=System.currentTimeMillis();
                if(time1!=null&&(Long.parseLong(time1)-time2<0)){
                    //重新设置锁的生存时间,返回旧的生存时间
                    //String a=null;
                    //synchronized (RedisTest.class){

                    String a = conn.getSet(key, String.valueOf(System.currentTimeMillis() + timeout));

                    // }


                    //如果旧的生存时间<当前时间，则表示我是第一个进行getset操作，我可以拿到锁，另一种那返回的锁的时候和上面一开始getExpire()
                    //方法获得的时候进行比较，如果相同则表示我是第一个getset的，不相同则表示在我之前已经有人进行过getset操作，那么我只能重新请求。
                    if(a!=null&&a.equals(time1)&&(Long.valueOf(a)-time2<0)){
                        conn.expire(key,5);
                        String time11=getExpire(key);
                        System.out.println(Thread.currentThread().getName()+"重新设置后锁的值为:"+time11+"//"+a);
                        System.out.println(Thread.currentThread().getName()+"我是第一个使用getset的人");
                        return true;
                    }else{
                        System.out.println(Thread.currentThread().getName()+"正在尝试重新获取锁中");
                        Thread.sleep(500);
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
    public String getExpire(String key){
        Jedis conn=pool.getResource();
        String expire=null;
        if(conn.exists(key)){
            System.out.println(Thread.currentThread().getName()+"key的值为:"+conn.get(key));
            expire=conn.get(key);
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
        if(conn.exists(key)){
            //获取锁的时间
            String time1=conn.get(key);
            //如果生存时间大于当前时间，则进行释放锁的操作,如果生存时间小于当前时间，则表示锁已经失效，那么可能已经被别人所占有，则不做操作
            long time=System.currentTimeMillis();
            if(time1!=null&&(Long.valueOf(time1)-time>0)){
                /*Transaction transaction=conn.multi();
                transaction.del(key);
                transaction.exec();*/
                conn.del(key);
                System.out.println("锁时间："+time1+"/系统时间:"+time);
                System.out.println(Thread.currentThread().getName()+"我删除锁了");
            }else{
                System.out.println("锁时间："+time1+"/系统时间:"+time);
                System.out.println(Thread.currentThread().getName()+"锁早已经被删除了");
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
    public String method(){
        return"我已经得到锁了，我正在处理自己的事情";
    }
}
