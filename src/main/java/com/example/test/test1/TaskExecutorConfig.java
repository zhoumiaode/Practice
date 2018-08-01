package com.example.test.test1;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;

/**
 * @ProjectName: test
 * @Package: com.example.test.test1
 * @ClassName: TaskExecutorConfig
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/31 15:52
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/31 15:52
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */

@Configuration
@ComponentScan("com.example.test.test1")
@EnableAsync
public class TaskExecutorConfig implements AsyncConfigurer {

    public Executor getAsyncExecutor(){

        ThreadPoolTaskExecutor taskExecutor =new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(25);
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }


}
