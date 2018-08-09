package com.example.test.httpMessageConverter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * @ProjectName: test
 * @Package: com.example.test.httpMessageConverter
 * @ClassName: SeeController
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/08 9:19
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/08 9:19
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */

@Controller
public class SeeController {

    @RequestMapping(value = "push",produces = "text/event-stream;charset=UTF-8")
    public @ResponseBody String push(){
        Random r=new Random();
        try {

            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "data:Test 1,2,3"+r.nextInt()+"\n\n";
    }
}
