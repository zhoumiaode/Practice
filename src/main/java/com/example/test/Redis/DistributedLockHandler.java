package com.example.test.Redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @ProjectName: test
 * @Package: com.example.test.Redis
 * @ClassName: DistributedLockHandler
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/22 10:54
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/22 10:54
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class DistributedLockHandler {
    private static final Integer Lock_Timeout = 3;

    private JedisPool jedis;

    public DistributedLockHandler(JedisPool pool){
        this.jedis=pool;
    }
    /**
     * 外部调用加锁的方法
     * @param lockKey 锁的名字
     * @param timeout 超时时间（放置时间长度，如：5L）
     * @return
     */
    public boolean tryLock(String lockKey, Long timeout) {
        try {
            Long currentTime = System.currentTimeMillis();//开始加锁的时间
            boolean result = false;

            while (true) {
                if ((System.currentTimeMillis() - currentTime) / 1000 > timeout) {//当前时间超过了设定的超时时间
                    System.out.println("Execute DistributedLockHandler.tryLock method, Time out.");
                    break;
                } else {
                    result = innerTryLock(lockKey);
                    if (result) {
                        break;
                    } else {
                        System.out.println("Try to get the Lock,and wait 100 millisecond....");
                        Thread.sleep(100);
                    }
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to run DistributedLockHandler.getLock method."+ e);
            return false;
        }
    }

    /**
     * 释放锁
     * @param lockKey 锁的名字
     */
    public void realseLock(String lockKey) {
        if(!checkIfLockTimeout(System.currentTimeMillis(), lockKey)){
            jedis.getResource().del(lockKey);
        }
    }

    /**
     * 内部获取锁的实现方法
     * @param lockKey 锁的名字
     * @return
     */
    private boolean innerTryLock(String lockKey) {

        long currentTime = System.currentTimeMillis();//当前时间
        String lockTimeDuration = String.valueOf(currentTime + Lock_Timeout + 1);//锁的持续时间
        Long result = jedis.getResource().setnx(lockKey, lockTimeDuration);

        if (result == 1) {
            return true;
        } else {
            if (checkIfLockTimeout(currentTime, lockKey)) {
                String preLockTimeDuration = jedis.getResource().getSet(lockKey, lockTimeDuration);
                if (currentTime > Long.valueOf(preLockTimeDuration)) {
                    return true;
                }
            }
            return false;
        }

    }

    /**
     * 判断加锁是否超时
     * @param currentTime 当前时间
     * @param lockKey 锁的名字
     * @return
     */
    private boolean checkIfLockTimeout(Long currentTime, String lockKey) {
        if (currentTime > Long.valueOf(jedis.getResource().get(lockKey))) {//当前时间超过锁的持续时间
            return true;
        } else {
            return false;
        }
    }

    public JedisPool getJedis() {
        return jedis;
    }

    public void setJedis(JedisPool jedis) {
        this.jedis = jedis;
    }
}
