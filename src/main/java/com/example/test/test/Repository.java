package com.example.test.test;

import java.util.LinkedList;

/**
 * @ProjectName: test
 * @Package: com.example.test.test
 * @ClassName: Repository
 * @Description: 生产者消费者例子的工厂类
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/24 13:38
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/24 13:38
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Repository implements AbstractRepository{

    //仓库存储的最大容量
    private final int MAX_SIZE=500;
    //仓库存储的载体
    private LinkedList list = new LinkedList();
    private static Object lock;


    /** 
    * @Description:  
    * @Param: [num]
    * @return: void 
    * @Author: zhoumiaode
    * @Date: 2018/07/24 
    */ 
    public  void produce(int num){

        synchronized (list){
            while((list.size()+num)>MAX_SIZE){
                System.out.println(Thread.currentThread().getName()+"【要生产的产品数量】:" + num + "\t【库存量】:" + list.size() + "\t暂时不能执行生产任务!");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for(int i=0;i<num;i++){
                list.add(new Object());
            }
            System.out.println(Thread.currentThread().getName()+"【已经生产产品数】:" + num + "\t【现仓储量为】:" + list.size());
            list.notifyAll();
        }
    }

    public void pop(int num){
        synchronized (list){
            while(list.size()<num){
                System.out.println(Thread.currentThread().getName()+"【要消费的产品数量】:" + num + "\t【库存量】:" + list.size() + "\t暂时不能执行生产任务!");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for(int i=0;i<num;i++){
                list.remove();
            }
            System.out.println(Thread.currentThread().getName()+"【已经消费产品数】:" + num + "\t【现仓储量为】:" + list.size());
            list.notifyAll();
        }

    }
}
