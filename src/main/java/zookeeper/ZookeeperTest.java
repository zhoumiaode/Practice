package zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

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
    private static ZooKeeper zookeeper;
    private static Stat stat=new Stat();

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private ZookeeperTest eventNoneWatcherThead=null;
    public static void main(String[] args) throws Exception{
        //客户端连接到ZK服务器
        zookeeper = new ZooKeeper("192.168.89.136:2181",5000,new ZookeeperTest());
        try{
            connectedSemaphore.await();
        }catch(InterruptedException e){}
        System.out.println("ZooKeeper session established.");
        //List<String> ac=zookeeper.getChildren("/zk-book",false);
        //创建节点
        String path="/zk-test-ephemerala";
        zookeeper.exists(path,true);
        zookeeper.getData(path,true,stat);
        zookeeper.delete(path,-1);//-1表示忽略版本
        System.out.println("同步读取节点内容：" + new String(zookeeper.getData(path,true,stat)));
        String path1 = zookeeper.create(path,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //System.out.println("Success create znode:"+path1);
        //zookeeper.exists(path,true);
        /*String path22 = zookeeper.create(path+"/1","123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);*/

        //zookeeper.delete(path,stat.getVersion());
        //zookeeper.setData(path,"123".getBytes(),-1);
        //List al=zookeeper.getChildren(path,true);
        //System.out.println(al);
        //System.out.println(zookeeper.exists(path1,new ZookeeperTest()));
        //String path2 = zookeeper.create("/zk-test-ephemeral-13","".getBytes(),
        //        ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
       // System.out.println("Success create znode:"+path2);
        //System.out.println(zookeeper.getState());

    }
    public void process(WatchedEvent event){
        System.out.println("21212"+event);
        if (Event.KeeperState.SyncConnected == event.getState()) {
            if(Event.EventType.None == event.getType() && null == event.getPath()){ // 连接时的监听事件
                System.out.println(event.getType());
                connectedSemaphore.countDown();
            } else if (event.getType() == Event.EventType.NodeDataChanged){ // 子节点内容变更时的监听
                try {
                    System.out.println(event.getType());
                    System.out.println("监听获得通知内容：data="
                            + new String(zookeeper.getData(event.getPath(),true,stat)));
                    System.out.println("监听获得通知Stat：czxid=" + stat.getCzxid()
                            + ";mzxid=" + stat.getMzxid() + ";version="  + stat.getVersion());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
