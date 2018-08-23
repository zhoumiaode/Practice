package zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import org.thymeleaf.expression.Ids;

import java.util.List;

/**
 * @ProjectName: test
 * @Package: zookeeper
 * @ClassName: CuratorTest
 * @Description: Curator客户端基本API的使用
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/23 16:04
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/23 16:04
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class CuratorTest {

    private static String IP="192.168.89.136:2181";
    private static int Time_Out=5000;
    private static int Session_Out=5000;
    private static RetryPolicy retry=new ExponentialBackoffRetry(1000,3);
    private static CuratorFramework client;
    private static Stat stat=new Stat();

    public CuratorTest(CuratorFramework client){
        this.client=client;
        this.client.start();
    }

    /** 
    * @Description:使用静态工程方法进行客户端的创建
    * @Param:
    * @return:  
    * @Author: zhoumiaode
    * @Date: 2018/08/23 
    */ 
    /*static{
        //第一个参数是IP地址，第二个参数是会话失效时间，第三个参数是连接超时时间，第四个是重试策略
        client= CuratorFrameworkFactory.newClient(IP,Session_Out,Time_Out,retry);
    }*/

    /** 
    * @Description:使用Fluent风格的创建
    * @Param:  
    * @return:  
    * @Author: zhoumiaode
    * @Date: 2018/08/23 
    */ 
  /*  static{
        //其中namespace表示该客户端所有的节点操作都在/test节点下操作
        client=CuratorFrameworkFactory.builder().connectString(IP).connectionTimeoutMs(Time_Out).
                sessionTimeoutMs(Session_Out).retryPolicy(retry).namespace("test").build();
    }*/

    public static void main(String[] args) throws Exception {
        //完成创建后通过start启动客户端
        CuratorFramework client= CuratorFrameworkFactory.newClient(IP,Session_Out,Time_Out,retry);
        CuratorTest curatorTest=new CuratorTest(client);
        //curatorTest.CreatePath("/asa/as","values");
        //curatorTest.getData("/ttt");
        //curatorTest.getChildenPath("/asq");
        curatorTest.updatePath("/ttt","aa");
    }

    /** 
    * @Description: 创建一个节点,其中creatingParentsIfNeeded表示如果没有父节点则自动创建父节点
    * @Param: [path, value] 
    * @return: void 
    * @Author: zhoumiaode
    * @Date: 2018/08/23 
    */ 
    public void CreatePath(String path,String value) throws Exception {
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).
                forPath(path,value.getBytes());
    }

    /** 
    * @Description: 删除一个节点，其中deletingChildrenIfNeeded表示递归删除所有子节点,guaranteed确定特殊条件下节点一定被删除，本质是重试
    * @Param: [path] 
    * @return: void 
    * @Author: zhoumiaode
    * @Date: 2018/08/23 
    */ 
    public void deletePath(String path) throws Exception {
        client.delete().guaranteed().deletingChildrenIfNeeded().withVersion(-1).forPath(path);
    }

    /** 
    * @Description: 获取指定节点的数据，其中storingStatIn返回节点的状态
    * @Param: [path] 
    * @return: void 
    * @Author: zhoumiaode
    * @Date: 2018/08/23 
    */ 
    public void getData(String path) throws Exception {
        byte[] a=client.getData().storingStatIn(stat).forPath(path);
        System.out.println("版本号："+stat.getVersion()+"/数据:"+a.toString());
    }

    /** 
    * @Description:获取指定节点下的所有子节点
    * @Param: [path] 
    * @return: java.util.List<java.lang.String> 
    * @Author: zhoumiaode
    * @Date: 2018/08/23 
    */ 
    public List<String> getChildenPath(String path) throws Exception {
        List<String> list=null;
        list=client.getChildren().forPath(path);
        if(list!=null&&list.size()>0){
            for(String o:list){
                System.out.println(o);
            }
        }
        return list;
    }

    /**
     * @Description:修改指定节点数据
     * @Param: [path]
     * @return: java.util.List<java.lang.String>
     * @Author: zhoumiaode
     * @Date: 2018/08/23
     */
    public void updatePath(String path,String value) throws Exception {
        client.setData().withVersion(-1).forPath(path,value.getBytes());
    }
}
