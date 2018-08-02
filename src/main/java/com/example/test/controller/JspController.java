package com.example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName: test
 * @Package: com.example.test.controller
 * @ClassName: JspController
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/02 12:44
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/02 12:44
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Controller
public class JspController {

    @RequestMapping(value = "hello1")
    public String hell1(){
        return "hello1";
    }
}
