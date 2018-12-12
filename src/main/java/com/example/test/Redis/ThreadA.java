package com.example.test.Redis;

/**
 * @ProjectName: test
 * @Package: com.example.test.Redis
 * @ClassName: ThreadA
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/21 14:18
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/21 14:18
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class ThreadA extends  Thread{
    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.seckill();
    }
}

 class Test {
    public static void main(String[] args) {
        Service service = new Service();
        for (int i = 0; i < 50; i++) {
            ThreadA threadA = new ThreadA(service);
            threadA.start();
        }
    }
}
