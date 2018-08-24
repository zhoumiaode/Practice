package zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: test
 * @Package: zookeeper
 * @ClassName: CuratorLock
 * @Description: 通过Curator实现分布式锁
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/23 16:01
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/23 16:01
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class CuratorLock {
    private static String ip="192.168.89.136:2181";
    private static CuratorFramework curatorFramework= CuratorFrameworkFactory.builder().retryPolicy(new ExponentialBackoffRetry(1000,3)).connectString(ip)
            .connectionTimeoutMs(5000).build();

    private static CountDownLatch order=new CountDownLatch(1);
    private static CountDownLatch service=new CountDownLatch(10);

    public static void main(String[] args)throws Exception{
        curatorFramework.start();
        //InterProcessMutex interProcessMutex=new InterProcessMutex(curatorFramework,"/pas");
        ExecutorService service1 = Executors.newCachedThreadPool(); //创建一个线程池
        for(int i=0;i<10;i++){
            Runnable runnable = new Runnable(){

                public void run(){
                    InterProcessMutex interProcessMutex=new InterProcessMutex(curatorFramework,"/pas");

                    try {
                        System.out.println("开始流水线工作啦");
                        order.await();
                        if(interProcessMutex.acquire(1, TimeUnit.SECONDS)){
                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                            String orderNo=sdf.format(new Date());
                            System.out.println("生成的订单号是："+orderNo);
                        }else{
                            System.out.println("dd");
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }finally{
                        try {
                            interProcessMutex.release();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        service.countDown();
                    }
                }
            };
            service1.execute(runnable);//为线程池添加任务
        }

        try {
            order.countDown();
            service.await();
            System.out.println("全部线程处理完毕");
        }catch (Exception e){

        }
    }
}
