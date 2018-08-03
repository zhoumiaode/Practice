package com.example.test.controller;

import com.example.test.httpClient.HttpAPIService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ProjectName: test
 * @Package: com.example.test.controller
 * @ClassName: HttpClientController
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/03 15:02
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/03 15:02
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@RestController
public class HttpClientController {

    @Resource
    private HttpAPIService httpAPIService;

    @RequestMapping("httpclient")
    public String test() throws Exception {
        String str = httpAPIService.doGet("https://www.baidu.com");
        System.out.println(str);
        return "hello";
    }
}
