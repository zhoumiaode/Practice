package com.example.test.controller;

import com.example.test.service.GirlService;
import com.example.test.thread.TimeThread;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @ProjectName: test
 * @Package: com.example.test.controller
 * @ClassName: TimeThreadController
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/25 16:47
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/25 16:47
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */

@RestController
public class TimeThreadController {
    @Resource
    private GirlService girlService;

    @PostMapping(value="method")
    public void method(){
        /*解析httpClient发送的参数*/
        try{

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            InputStream inputStream = request.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
            String strMessage="";
            String strResponse="";
            while ((strMessage = reader.readLine()) != null) {
                strResponse += strMessage;
            }
            reader.close();
            inputStream.close();
            JSONObject json = JSONObject.fromObject(strResponse);
            String id=json.getString("id");
            TimeThread t=new TimeThread(id,girlService);
            t.start();
            if(!t.isInterrupted()){
                t.interrupt();
                System.out.println(t.isInterrupted()+""+Thread.activeCount());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
