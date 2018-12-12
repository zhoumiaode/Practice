package com.example.test.Listen;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * @ProjectName: test
 * @Package: com.example.test.Listen
 * @ClassName: RequestListen
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/03 9:20
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/03 9:20
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@WebListener
public class RequestListen implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("request监听死亡");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("request监听启动");
    }
}
