package com.example.test.Listen;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @ProjectName: test
 * @Package: com.example.test.Listen
 * @ClassName: TestListen
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/03 9:13
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/03 9:13
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@WebListener
public class TestListen implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("监听器初始化启动");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("监听器初始化结束");
    }
}
