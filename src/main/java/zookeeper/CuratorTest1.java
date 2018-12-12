package zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

import java.util.List;

/**
 * @ProjectName: test
 * @Package: zookeeper
 * @ClassName: CuratorTest1
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/31 15:28
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/31 15:28
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class CuratorTest1 {

    private final static String url="192.168.89.136:2181";
    private final static String path="/curatorTest";
    private final static RetryPolicy retry=new ExponentialBackoffRetry(1000,3);
    public static void main(String[] args) throws Exception {
        CuratorFramework client= CuratorFrameworkFactory.newClient(url,5000,5000,retry);
        CuratorFramework clients=CuratorFrameworkFactory.builder().connectString(url).connectionTimeoutMs(5000).
                sessionTimeoutMs(5000).retryPolicy(retry).build();
        client.start();
        clients.start();
        NodeCache cache=new NodeCache(client,path);
        cache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("改变的内容为："+cache.getCurrentData().getPath()+":"+new String(cache.getCurrentData().getData()));
            }
        });
        cache.start();
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).
                forPath(path,"tye".getBytes());
        Thread.sleep(1000);
        TreeCache treeCache=new TreeCache(client,path);
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework clientss, TreeCacheEvent event) throws Exception {
                ChildData data=event.getData();
                switch (event.getType()){
                    case NODE_ADDED:
                        System.out.println("添加："+data.getPath()+"/"+data.getData());
                        break;
                    case NODE_UPDATED:
                        System.out.println("更新:"+data.getPath()+"/"+data.getData());
                        break;
                    case NODE_REMOVED:
                        System.out.println("移除："+data.getPath()+"/"+data.getData());
                        break;
                    default:
                        System.out.println("默认");

                }
            }
        });
        treeCache.start();
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath(path+"/t1","123".getBytes());
        Thread.sleep(1000);
        client.delete().guaranteed().deletingChildrenIfNeeded().forPath(path+"/t1");
        Thread.sleep(1000);

        PathChildrenCache pathChildrenCache=new PathChildrenCache(client,path,true);
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                ChildData data=event.getData();
                switch (event.getType()){
                    case CHILD_ADDED:
                        System.out.println("添加1："+data.getPath()+"/"+data.getData());
                        break;
                    case CHILD_UPDATED:
                        System.out.println("更新1:"+data.getPath()+"/"+data.getData());
                        break;
                    case CHILD_REMOVED:
                        System.out.println("移除1："+data.getPath()+"/"+data.getData());
                        break;
                    default:
                        System.out.println("默认1");

                }
            }
        });
        pathChildrenCache.start();
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath(path+"/t1","123".getBytes());
        Thread.sleep(1000);
        client.delete().guaranteed().deletingChildrenIfNeeded().forPath(path+"/t1");
        Thread.sleep(1000);
        Thread.sleep(1000);
    }
}
