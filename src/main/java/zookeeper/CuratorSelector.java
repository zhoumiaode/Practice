package zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: test
 * @Package: zookeeper
 * @ClassName: CuratorSelector
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/29 9:22
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/29 9:22
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class CuratorSelector {

    private static List<LeaderSelector> selectors=new ArrayList<LeaderSelector>();
    private static List<CuratorFramework> clients=new ArrayList<CuratorFramework>();
    private final static String path="/test2";
    private final static String url="192.168.89.136:2181";
    private static RetryPolicy retryPolicy=new ExponentialBackoffRetry(1000,5);

    public static CuratorFramework getClient(){
        CuratorFramework client= CuratorFrameworkFactory.builder().connectString(url).connectionTimeoutMs(5000).
                sessionTimeoutMs(5000).retryPolicy(retryPolicy).build();
        client.start();
        return client;
    }

    public static void main(String[] args){
        try {
            for (int i=0;i<10;i++){
                CuratorFramework client=getClient();
                final String name="Client#"+i;
                LeaderSelector selector=new LeaderSelector(client, path, new LeaderSelectorListener() {
                    @Override
                    public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                        System.out.println("姓名:"+name+"I am a Leader");
                    }

                    @Override
                    public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {

                    }
                });
                selector.autoRequeue();//可以多次进行选举
                selector.start();
                selectors.add(selector);
            }
            Thread.sleep(Integer.MAX_VALUE);
        }catch (Exception e){
            e.printStackTrace();
        }finally {

            for(CuratorFramework client:clients){
                CloseableUtils.closeQuietly(client);
            }
            for(LeaderSelector selector : selectors){
                CloseableUtils.closeQuietly(selector);
            }
        }
    }
}
