package com.example.test.test;

/**
 * @ProjectName: test
 * @Package: com.example.test.test
 * @ClassName: TimeThread
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/25 10:18
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/25 10:18
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class TimeThread extends Thread {

    private int time;

    public TimeThread(int time) {
        this.time = time;
    }

    @Override
    public void run() {
        System.out.println(time);
    }
}
