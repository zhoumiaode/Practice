package zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @ProjectName: test
 * @Package: zookeeper
 * @ClassName: CuratorWatcher2
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/24 11:19
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/24 11:19
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class CuratorWatcher2 {
    private static final String CONNECT_ADDR = "192.168.89.136:2181";
    private static final int SESSION_TIMEOUT = 5000;

    public static void main(String[] args) throws Exception {
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 10);
        CuratorFramework curator = CuratorFrameworkFactory.builder().connectString(CONNECT_ADDR)
                .sessionTimeoutMs(SESSION_TIMEOUT).retryPolicy(policy).build();
        curator.start();
        //第三个参数表示是否接收节点数据内容
        PathChildrenCache childrenCache = new PathChildrenCache(curator, "/super", true);
        /**
         * 如果不填写这个参数，则无法监听到子节点的数据更新
         如果参数为PathChildrenCache.StartMode.BUILD_INITIAL_CACHE，则会预先创建之前指定的/super节点
         如果参数为PathChildrenCache.StartMode.POST_INITIALIZED_EVENT，效果与BUILD_INITIAL_CACHE相同，只是不会预先创建/super节点
         参数为PathChildrenCache.StartMode.NORMAL时，与不填写参数是同样的效果，不会监听子节点的数据更新操作
         */
        childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        childrenCache.getListenable().addListener((framework, event) -> {
            switch (event.getType()) {
                case CHILD_ADDED:
                    System.out.println("CHILD_ADDED，类型：" + event.getType() + "，路径：" + event.getData().getPath() + "，数据：" +
                            new String(event.getData().getData()) + "，状态：" + event.getData().getStat());
                    break;
                case CHILD_UPDATED:
                    System.out.println("CHILD_UPDATED，类型：" + event.getType() + "，路径：" + event.getData().getPath() + "，数据：" +
                            new String(event.getData().getData()) + "，状态：" + event.getData().getStat());
                    break;
                case CHILD_REMOVED:
                    System.out.println("CHILD_REMOVED，类型：" + event.getType() + "，路径：" + event.getData().getPath() + "，数据：" +
                            new String(event.getData().getData()) + "，状态：" + event.getData().getStat());
                    break;
                default:
                    break;
            }
        });

        //curator.create().forPath("/super", "123".getBytes());
        //curator.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/super/c5", "c1内容".getBytes());
        //经测试，不会监听到本节点的数据变更，只会监听到指定节点下子节点数据的变更
        //curator.setData().forPath("/super", "456".getBytes());
        curator.setData().forPath("/super/c3", "c1新内容".getBytes());
        curator.delete().guaranteed().deletingChildrenIfNeeded().forPath("/super/c5");
        Thread.sleep(5000);
        curator.close();
    }
}
