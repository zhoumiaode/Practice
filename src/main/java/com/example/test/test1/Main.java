package com.example.test.test1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.Executor;

/**
 * @ProjectName: test
 * @Package: com.example.test.test1
 * @ClassName: Main
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/31 16:13
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/31 16:13
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Main {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
        AsyncTaskService asyncTaskService=context.getBean(AsyncTaskService.class);
        for(int i=0;i<10;i++){
            asyncTaskService.executeAsyncTask(i);
            asyncTaskService.executeAsyncTaskPlus(i);
        }
        context.close();
    }
}
