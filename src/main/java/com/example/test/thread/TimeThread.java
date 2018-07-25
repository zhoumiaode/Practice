package com.example.test.thread;

import com.example.test.domain.Girl;
import com.example.test.service.GirlService;

/**
 * @ProjectName: test
 * @Package: com.example.test.thread
 * @ClassName: TimeThread
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/25 16:40
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/25 16:40
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class TimeThread extends Thread {

    private String id;
    private GirlService girlService;
    private  boolean flag=true;

    public void setId(String id) {
        this.id = id;
    }

    public TimeThread(String id,GirlService girlService){
        this.id=id;
        this.girlService=girlService;
    }


    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public void run(){
        while(flag)
            try {
            Girl girl=new Girl();
                synchronized(TimeThread.class){
                    girl=this.girlService.findOne(Integer.getInteger(id));
                    if(girl!=null){
                        this.girlService.deleteById(Integer.getInteger(id));
                        System.out.println(Thread.currentThread().getName()+"秒杀成功");
                    }
                }

                if(girl==null){
                    System.out.println(Thread.currentThread().getName()+"秒杀失败");
                }
                flag=false;
            } catch (Exception e) {
                // TODO Auto-generated catch block
            }
        }
    }
