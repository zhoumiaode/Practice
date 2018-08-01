package com.example.test.test1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: test
 * @Package: com.example.test.test1
 * @ClassName: ConditionConfig
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/01 9:30
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/01 9:30
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Configuration
public class ConditionConfig {

    @Bean
    @Conditional(WindowsCondition.class)
    public ListService windowsListService(){
        return new WindowsService();
    }

    @Bean
    @Conditional(LinuxCondition.class)
    public ListService linuxListService(){
        return new LinuxService();
    }
}
