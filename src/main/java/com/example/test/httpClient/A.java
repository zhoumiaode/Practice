package com.example.test.httpClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: test
 * @Package: com.example.test.httpClient
 * @ClassName: A
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/03 15:35
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/03 15:35
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Configuration
public class A {
    @Bean
    public HttpAPIService getHttp(){
        return new HttpAPIService();
    }
}
