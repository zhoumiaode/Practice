package zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @ProjectName: test
 * @Package: zookeeper
 * @ClassName: TreeCacheDemo
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/24 15:38
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/24 15:38
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class TreeCacheDemo {private static final String PATH = "/example";
    private static String IP="192.168.89.136:2181";
    private static int Time_Out=5000;
    private static int Session_Out=5000;
    private static RetryPolicy retry=new ExponentialBackoffRetry(1000,3);
    public static void main(String[] args) throws Exception {
        CuratorFramework client= CuratorFrameworkFactory.newClient(IP,Session_Out,Time_Out,retry);
        //CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(), new ExponentialBackoffRetry(1000, 3));
        client.start();
        client.create().creatingParentsIfNeeded().forPath(PATH);
        TreeCache cache = new TreeCache(client, PATH);
        TreeCacheListener listener = (client1, event) ->
                System.out.println("事件类型：" + event.getType() +
                        " | 路径：" + (null != event.getData() ? event.getData().getPath() : null));
        cache.getListenable().addListener(listener);
        cache.start();client.create().creatingParentsIfNeeded().forPath("/example/ee");

        client.setData().forPath("/example/ee", "01".getBytes());
        Thread.sleep(100);
        client.setData().forPath("/example/ee", "02".getBytes());
        Thread.sleep(100);
        client.delete().deletingChildrenIfNeeded().forPath("/example/ee");
        Thread.sleep(1000 * 2);
        cache.close();
        client.close();
        System.out.println("OK!");
    }

}
