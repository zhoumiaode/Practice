package com.example.test.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: test
 * @Package: com.example.test.controller
 * @ClassName: TTC
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/25 17:33
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/25 17:33
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */

@RestController
public class TTC {

    @PostMapping(value="t")
    public void men(){
        System.out.println(12);
    }
}
