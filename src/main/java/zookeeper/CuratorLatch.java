package zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: test
 * @Package: zookeeper
 * @ClassName: CuratorLatch
 * @Description: Curator使用LeaderLatch进行选举
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/29 9:06
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/29 9:06
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class CuratorLatch {

    private static List<CuratorFramework> clients=new ArrayList<CuratorFramework>();
    private static List<LeaderLatch> latchs=new ArrayList<LeaderLatch>();
    private final static String URL="192.168.89.136:2181";
    private final static String path="/test";
    private final static RetryPolicy retry=new ExponentialBackoffRetry(1000,5);

    private static CuratorFramework getClient(){
        CuratorFramework client= CuratorFrameworkFactory.builder().connectString(URL).sessionTimeoutMs(5000).
                connectionTimeoutMs(5000).retryPolicy(retry).build();
        client.start();
        return  client;
    }

    public static void main(String[] args){
        try {
            for(int i=0;i<10;i++){
                CuratorFramework client=getClient();
                clients.add(client);
                LeaderLatch latch=new LeaderLatch(client,path,"client#"+i);
                latch.addListener(new LeaderLatchListener() {
                    @Override
                    public void isLeader() {
                        System.out.println(latch.getId()+"I am a Leader");
                    }

                    @Override
                    public void notLeader() {
                        System.out.println(latch.getId()+"I am not a Leader");
                    }
                });
                latch.start();
                latchs.add(latch);
            }
            Thread.sleep(Integer.MAX_VALUE);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            for(CuratorFramework client:clients){
                CloseableUtils.closeQuietly(client);
            }
            for(LeaderLatch latch:latchs){
                CloseableUtils.closeQuietly(latch);
            }
        }
    }
}
