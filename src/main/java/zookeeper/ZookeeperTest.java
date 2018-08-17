package zookeeper;

import org.apache.zookeeper.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @ProjectName: test
 * @Package: zookeeper
 * @ClassName: ZookeeperTest
 * @Description: ZK创建回话以及同步节点
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/17 14:15
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/17 14:15
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class ZookeeperTest implements Watcher{
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    public static void main(String[] args) throws Exception{
        //客户端连接到ZK服务器
        ZooKeeper zookeeper = new ZooKeeper("192.168.89.136:2181",5000,new ZookeeperTest());
        //创建节点
        String path="/zk-test-ephemeral1-";
        String path1 = zookeeper.create(path,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        System.out.println("Success create znode:"+path1);

        /*String path22 = zookeeper.create(path+"/1","123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);*/
        List al=zookeeper.getChildren(path,true);
        System.out.println(al);
        //System.out.println(zookeeper.exists(path1,new ZookeeperTest()));
        String path2 = zookeeper.create("/zk-test-ephemeral-","".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("Success create znode:"+path2);
        System.out.println(zookeeper.getState());
        try{
            connectedSemaphore.await();
        }catch(InterruptedException e){}
        System.out.println("ZooKeeper session established.");
    }
    public void process(WatchedEvent event){
        System.out.println("Receive watched event:"+event);
        if(Event.KeeperState.SyncConnected == event.getState()){
            connectedSemaphore.countDown();
        }
    }
}
