package com.example.test.thread;

import com.example.test.domain.Girl;
import com.example.test.service.GirlService;

import javax.persistence.criteria.CriteriaBuilder;

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

    private String ids;
    private GirlService girlService;
    private boolean flag = true;

    public TimeThread(String ids, GirlService girlService) {
        this.ids = ids;
        this.girlService = girlService;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public GirlService getGirlService() {
        return girlService;
    }

    public void setGirlService(GirlService girlService) {
        this.girlService = girlService;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void run() {
        while (flag) {
            Girl girl = null;
            boolean fl=false;
            try {
                synchronized (TimeThread.class) {
                    Integer id= Integer.parseInt(this.getIds());
                         //fl=this.girlService.findById(id);
                    girl=this.girlService.findOne(id);
                    if (girl!=null) {
                        this.girlService.deleteById(id);
                        System.out.println(Thread.currentThread().getName() + "秒杀成功");
                    }
                }
                if (girl==null) {
                    System.out.println(Thread.currentThread().getName() + "秒杀失败");
                }
                flag = false;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{

            }
        }
    }
}
