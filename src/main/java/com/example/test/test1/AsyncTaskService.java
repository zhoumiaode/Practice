package com.example.test.test1;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: test
 * @Package: com.example.test.service
 * @ClassName: AsyncTaskService
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/31 16:10
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/31 16:10
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Service
public class AsyncTaskService {

    @Async
    public void executeAsyncTask(Integer i){
        System.out.println("执行异步任务:"+i);
    }

    @Async
    public void executeAsyncTaskPlus(Integer i){
        System.out.println("执行异步任务+l:"+(i+1));
    }
}
