package com.example.test.httpClient;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @ProjectName: test
 * @Package: com.example.test.httpClient
 * @ClassName: PostController
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/08 14:58
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/08 14:58
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Controller
public class PostController {

    @ResponseBody
    @PostMapping(value = "pp")
    public String post(){

        String result="";
        String url="http://192.168.89.136:8888/ppp";
        SortedMap<Object,Object> map=new TreeMap<Object, Object>();
        map.put("a","a");
        map.put("b","b");
        try {
            result=PostTest.post(url,map.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
