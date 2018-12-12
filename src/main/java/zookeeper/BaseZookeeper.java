package zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

/**
 * @ProjectName: test
 * @Package: zookeeper
 * @ClassName: BaseZookeeper
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/23 10:25
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/23 10:25
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class BaseZookeeper implements Watcher {

    private static ZooKeeper zooKeeper;
    private static String host="192.168.89.136:2181";
    private static int TimeOut=3000;
    private static Stat stat=new Stat();

    static {
        try {
            zooKeeper=new ZooKeeper(host,TimeOut,new BaseZookeeper());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 
    * @Description:  创建节点
    * @Param: [path, value] 
    * @return: boolean 
    * @Author: zhoumiaode
    * @Date: 2018/08/23 
    */ 
    public  boolean CreatePath(String path,String value)throws Exception{
        if(zooKeeper.exists(path,true)!=null){
            return false;
        }else {
            zooKeeper.create(path,value.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            return true;
        }
    }

    /** 
    * @Description: 修改节点数据
    * @Param: [path] 
    * @return: void 
    * @Author: zhoumiaode
    * @Date: 2018/08/23 
    */ 
    public void setData(String path) throws Exception{
        byte[] b=zooKeeper.getData(path,true,stat);
        if(b!=null){
            zooKeeper.setData(path,"haha".getBytes(),stat.getVersion());
        }
    }
    /** 
    * @Description: 删除节点
    * @Param: [path] 
    * @return: void 
    * @Author: zhoumiaode
    * @Date: 2018/08/23 
    */ 
    public void deletePath(String path) throws  Exception{
        if(zooKeeper.exists(path,true)!=null){
            zooKeeper.delete(path,-1);
        }
    }

    /** 
    * @Description: 获取指定节点下的子节点
    * @Param: [path] 
    * @return: java.util.List<java.lang.String> 
    * @Author: zhoumiaode
    * @Date: 2018/08/23 
    */ 
    public List<String> getChilden(String path)throws Exception{
        List<String> list = null;
        if(zooKeeper.exists(path,true)!=null){
            list=zooKeeper.getChildren(path,true);
        }
        return list.size()==0?null:list;
    }


    /** 
    * @Description:关闭zk连接
    * @Param: [] 
    * @return: void 
    * @Author: zhoumiaode
    * @Date: 2018/08/23 
    */ 
    public void close()throws Exception{
        if(zooKeeper!=null){
            zooKeeper.close();
        }
    }
    public static void main(String[] args){

        BaseZookeeper baseZookeeper=new BaseZookeeper();
        try {
            //baseZookeeper.CreatePath("/k2","value");
            //baseZookeeper.deletePath("/k1");

            //baseZookeeper.setData("/k2");
            List<String> list=baseZookeeper.getChilden("/k1");
            String a=list==null?"没有子节点":"好多子节点";
            System.out.println(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void process(WatchedEvent event) {
        if(Event.KeeperState.SyncConnected==event.getState()){
            if(Event.EventType.None==event.getType()){
                System.out.println("连接服务器成功");
            }else if(Event.EventType.NodeDeleted==event.getType()){
                System.out.println("删除节点操作");
            }else if(Event.EventType.NodeDataChanged==event.getType()){
                System.out.println("节点数据操作");
            }else if(Event.EventType.NodeCreated==event.getType()){
                System.out.println("创建节点操作");
            }else if(Event.EventType.NodeChildrenChanged==event.getType()){
                System.out.println("子节点操作");
            }
        }else if(Event.KeeperState.Disconnected==event.getState()){
            System.out.println("连接服务器失败");
        }else if(Event.KeeperState.AuthFailed==event.getState()){
            System.out.println("连接服务器权限受限");
        }else if(Event.KeeperState.Expired==event.getState()){
            System.out.println("会话失效");
        }
    }
}
