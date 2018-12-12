package zookeeper;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher;

import java.util.List;

/**
 * @ProjectName: test
 * @Package: zookeeper
 * @ClassName: ZKClient
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/23 13:28
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/23 13:28
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class ZKClient {

    private static String ip="192.168.89.136:2181";
    private static int TimeOut=5000;
    public static void main(String[] args) throws InterruptedException {

        ZkClient client=new ZkClient(ip,TimeOut);
        client.subscribeChildChanges("/pppp", new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println("路径" + s +"下面的子节点变更。子节点为：" + list );
            }
        });
        //client.createPersistent("/ppp/p3",true);
        //client.createPersistent("/ppp/p2",true);
        client.subscribeDataChanges("/ppp/p3", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("节点数据变化："+s+"-数据："+o.toString());
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("节点数据删除:"+s);
            }
        });
        client.writeData("/ppp/p3","pppppa");
        //client.delete("/ppp/p3");
        Thread.sleep(5000);
    }
}
