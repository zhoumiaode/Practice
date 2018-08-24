package zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @ProjectName: test
 * @Package: zookeeper
 * @ClassName: CuratorWatcher1
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/23 17:59
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/23 17:59
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class CuratorWatcher1 {
    private static final String CONNECT_ADDR = "192.168.89.136:2181";
    private static final int SESSION_TIMEOUT = 5000;

    public static void main(String[] args) throws Exception {
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 10);
        CuratorFramework curator = CuratorFrameworkFactory.builder().connectString(CONNECT_ADDR)
                .sessionTimeoutMs(SESSION_TIMEOUT).retryPolicy(policy).build();
        curator.start();
        //最后一个参数表示是否进行压缩
        NodeCache cache = new NodeCache(curator, "/super", false);

        //只会监听节点的创建和修改，删除不会监听
        cache.getListenable().addListener(() -> {
            System.out.println("路径：" + cache.getCurrentData().getPath());
            System.out.println("数据：" + new String(cache.getCurrentData().getData()));
            System.out.println("状态：" + cache.getCurrentData().getStat());
        });
        cache.start(true);
        curator.create().forPath("/super", "1234".getBytes());
        Thread.sleep(1000);
        //curator.setData().forPath("/super", "5678".getBytes());
        //Thread.sleep(1000);
        curator.delete().forPath("/super");
        //Thread.sleep(5000);
        cache.close();
        curator.close();

    }
}
