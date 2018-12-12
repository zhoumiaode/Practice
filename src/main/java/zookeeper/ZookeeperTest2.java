package zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @ProjectName: test
 * @Package: zookeeper
 * @ClassName: ZookeeperTest2
     * @Description: ZK创建会话以及异步节点
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/17 14:52
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/17 14:52
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class ZookeeperTest2 implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zookeeper = new ZooKeeper("192.168.89.136:2181",5000,//
                new ZookeeperTest2());
        connectedSemaphore.await();
        long sessionId = zookeeper.getSessionId();
        byte[] passwd = zookeeper.getSessionPasswd();

        //Use illegal sessionId and sessionPassWd
        zookeeper = new ZooKeeper("192.168.89.136:2181",5000,//
                new ZookeeperTest2(),//
                1l,//
                "test".getBytes());
        //Use correct sessionId and sessionPassWd
        zookeeper = new ZooKeeper("192.168.89.136:2181",5000,//
                new ZookeeperTest2(),//
                sessionId,//
                passwd);
        Thread.sleep(Integer.MAX_VALUE);
    }
    public void process(WatchedEvent event){
        System.out.println("Receive watched event:"+event);
        if(Event.KeeperState.SyncConnected == event.getState()){
            connectedSemaphore.countDown();
        }
    }
}
