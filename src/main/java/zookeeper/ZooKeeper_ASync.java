package zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @ProjectName: test
 * @Package: zookeeper
 * @ClassName: ZooKeeper_ASync
 * @Description: ZK异步创建节点
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/17 16:35
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/17 16:35
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class ZooKeeper_ASync implements Watcher{
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zookeeper = new ZooKeeper("192.168.89.136:2181",5000,new ZooKeeper_ASync());
        connectedSemaphore.await();
        long sessionId=zookeeper.getSessionId();
        byte[] b=zookeeper.getSessionPasswd();

        zookeeper.create("/zk-test-ephemeral-2","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
                new IStringCallback(),"I am contest.");
        zookeeper.create("/zk-test-ephemeral-1","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL,new IStringCallback(),"I am context.");
        zookeeper.create("/zk-test-ephemeral-","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL,new IStringCallback(),"I am context.");

        zookeeper.create("/zk-test-ephemeral-/1","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL,new IStringCallback(),"I am context.");

        Thread.sleep(Integer.MAX_VALUE);
    }

    public void process(WatchedEvent event){
        if(Watcher.Event.KeeperState.SyncConnected == event.getState()){
            connectedSemaphore.countDown();
        }
    }
}

class IStringCallback implements AsyncCallback.StringCallback{
    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        System.out.println("Create path result:【"+rc+","+path+","+","
                + ctx+", real path name:"+ name);
    }
}
